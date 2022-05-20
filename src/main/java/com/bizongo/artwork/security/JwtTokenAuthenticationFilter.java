package com.bizongo.artwork.security;

import com.bizongo.artwork.communicator.Communicator;
import com.bizongo.artwork.security.config.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private Communicator communicator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Get the request URI
        String path = request.getRequestURI();

        // Ignore authentication if its not a REST API Request
        if (path.contains("actuator") || "/favicon.ico".equals(path) || path.contains("swagger")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = "";
        String secretToken = "";

        // Get JWT Token from headers
        if (request.getHeader("authorization") != null) {
            jwtToken = request.getHeader("authorization").replace("Bearer ", "");
        }

        // Get secret-token from headers
        if (request.getHeader("secret-token") != null) {
            secretToken = request.getHeader("secret-token");
        }

        try {

            // Return Exception if JWT is not in headers
            if (jwtToken != null && !jwtToken.isEmpty()) {

                // Build request for UMS to validate JWT
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Service-Token", jwtConfig.getUmsSecret());

                HashMap<String, String> params = new HashMap<>();
                params.put("jwt_token", jwtToken);

                communicator.setUrl(jwtConfig.getValidateEndpoint());
                communicator.setRequestType(Communicator.Type.POST);
                communicator.setHeaders(headers);
                ResponseEntity<Response> responseEntity = communicator.request(Optional.of(params));
                Response authResponse = responseEntity.getBody();

                //If response is successful authenticate the requestCommunicatorTest
                if (authResponse.isSuccessful()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ResponseBody responseBody = authResponse.body();
                    Map authenticatedUser = objectMapper.readValue(responseBody.string(), Map.class);

                    Map<String, Object> userObject = objectMapper.readValue(authenticatedUser.get("user").toString(), Map.class);

                    String email = (String) userObject.get("email");
                    String firstName = (String) userObject.get("first_name");
                    String lastName = (String) userObject.get("last_name");
                    String userName = firstName + " " + lastName;
                    Integer user_id = (Integer) userObject.get("id");
                    Integer jwtCompanyId = (Integer) userObject.get("company_id");
                    Map<String, Object> userDataMap = new HashMap<>();

                    userDataMap.put("user", email);
                    userDataMap.put("userName", userName);
                    userDataMap.put("user_id", user_id);
                    userDataMap.put("jwtCompanyId", jwtCompanyId);
                    userDataMap.put("jwtToken", jwtToken);
                    userDataMap.put("xRequester", request.getHeader("X-requester"));

                    if (userObject.get("services") != null) {
                        userDataMap.put("services", (ArrayList<Map<String, Object>>) userObject.get("services"));
                    }

                    if (userObject.get("roles") != null) {
                        userDataMap.put("roles", (ArrayList<Map<String, Object>>) userObject.get("roles"));
                    }

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDataMap, jwtToken, null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    String errorMessage = String.format("jwt is invalid or incorrect. UMS error code %s, JWT_TOKEN: %s ", responseEntity.getStatusCode(), jwtToken);
                    throw new Exception(errorMessage);
                }
            } else if (secretToken != null && !secretToken.isEmpty()) {
                if (secretToken.equals(jwtConfig.getSecret())) {
                    Map<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("jwtToken", jwtToken);

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDataMap, secretToken, null);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    throw new Exception(String.format("secret token is invalid or incorrect, JWT_TOKEN: %s", jwtToken));
                }

            } else {
                throw new Exception("jwt/secret token is invalid or incorrect");
            }

        } catch (Exception e) {
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            e.printStackTrace();

            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        // go to the next filter in the filter chain
        filterChain.doFilter(request, response);
    }
}
