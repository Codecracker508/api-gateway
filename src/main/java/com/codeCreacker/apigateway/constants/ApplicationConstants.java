package com.codeCreacker.apigateway.constants;

import java.util.ArrayList;
import java.util.List;

public interface ApplicationConstants {
    String[] WHITELIST_URLS = {
            "/newUser/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**"
    };

    String[] MICROSERVICES = {
            "user-service"
    };

    String SECRET_KEY = "&@=@5fs;IyMAHmq";

    interface Headers {
        String INTERNAL_REQUEST = "X-Internal-Request";
    }
}
