package com.bizongo.artwork.security.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

@Data
public class JwtConfig {

    @Value("${jwt.prefix:Bearer }")
    private String prefix;

    @Value("${secret-token}")
    private String secret;

    @Value("${ums.url}")
    private String umsUrl;

    @Value("${ums.validate.api}")
    private String validateRoute;

    @Value("${ums.fetch-user.api}")
    private String fetchUserUrl;

    @Value("${ums.fetch-company.api}")
    private String fetchCompanyUrl;

    @Value("${ums.secret-token}")
    private String umsSecret;

    private String validateEndpoint;

    private String fetchUserEndpoint;

    private String fetchCompanyEndpoint;

    private String fetchCentreEndpoint;

    private String fetchAdminUserUrlEndPoint;

    @PostConstruct
    public void init(){
        validateEndpoint = umsUrl+validateRoute;
        fetchUserEndpoint = umsUrl+fetchUserUrl;
        fetchCompanyEndpoint = umsUrl+fetchCompanyUrl;
    }

}
