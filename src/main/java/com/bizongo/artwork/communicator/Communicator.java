package com.bizongo.artwork.communicator;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Component
public class Communicator {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private String url;

    private Type requestType;

    private Map<String, String> headers;

    public enum Type {
        POST,
        GET,
        DELETE,
        PUT
    }

    public ResponseEntity<Response> request(Optional<HashMap<String, String>> param) throws Exception {

        Request request = this.createRequestObject(param);

        OkHttpClient httpClient = new OkHttpClient();
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }


    public ResponseEntity<Response> postRequest(Optional<HashMap<String, Object>> param) throws Exception {

        Request request = this.createPostRequestObject(param);

        OkHttpClient httpClient = new OkHttpClient();
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }


    public ResponseEntity<Response> requestAsync(Optional<HashMap<String, String>> param) throws Exception {

        Request request = this.createRequestObject(param);

        OkHttpClient httpClient = new OkHttpClient();
        Response response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }


    private Request createRequestObject(Optional<HashMap<String, String>> param) throws IOException {
        Headers headerBuild = Headers.of(this.headers);
        Request.Builder requestBuilder = new Request.Builder();
        RequestBody requestBody = null;
        ObjectMapper mapper = new ObjectMapper();

        requestBuilder.headers(headerBuild);

        if (param.isPresent()) {
            HashMap<String, String> paramObject = param.get();
            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.url).newBuilder();

            if (this.requestType.equals(Type.GET) || this.requestType.equals(Type.DELETE)) {
                paramObject.forEach((key, value) -> urlBuilder.addQueryParameter(key, value));
            } else {
                try {
                    Map<String, Integer> params = new HashMap<>();
                    paramObject.forEach((key, value) -> params.put(key, Integer.valueOf(value)));
                    requestBody = RequestBody.create(JSON, mapper.writeValueAsString(params));
                } catch (NumberFormatException nfe) {
                    requestBody = RequestBody.create(JSON, mapper.writeValueAsString(paramObject));
                }
            }
            this.url = urlBuilder.build().toString();
            requestBuilder.url(this.url);

            switch (this.requestType) {
                case GET:
                    requestBuilder.get();
                    break;

                case POST:
                    requestBuilder.post(requestBody);
                    break;

                case PUT:
                    requestBuilder.put(requestBody);
                    break;

                case DELETE:
                    requestBuilder.delete();
                    break;

                default:
                    throw new IOException("Invalid Request type");
            }
        }

        return requestBuilder.build();
    }


    private Request createPostRequestObject(Optional<HashMap<String, Object>> param) throws IOException {
        Headers headerBuild = Headers.of(this.headers);
        Request.Builder requestBuilder = new Request.Builder();
        RequestBody requestBody = null;
        ObjectMapper mapper = new ObjectMapper();

        requestBuilder.headers(headerBuild);

        if (param.isPresent()) {
            HashMap<String, Object> paramObject = param.get();
            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.url).newBuilder();

            try {
                requestBody = RequestBody.create(JSON, mapper.writeValueAsString(paramObject.get("payload")));
            } catch (NumberFormatException nfe) {
                requestBody = RequestBody.create(JSON, mapper.writeValueAsString(paramObject.get("payload")));
            }

            this.url = urlBuilder.build().toString();
            requestBuilder.url(this.url);

            switch (this.requestType) {
                case POST:
                    requestBuilder.post(requestBody);
                    break;

                default:
                    throw new IOException("Invalid Request type");
            }
        }

        return requestBuilder.build();
    }
}
