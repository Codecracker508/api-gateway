package com.codeCreacker.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.codeCreacker.apigateway.constants.ApplicationConstants.Headers.INTERNAL_REQUEST;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("MyHeader", "MyURI").addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/user-service/**")
                        .filters(f -> f.addRequestHeader(INTERNAL_REQUEST, String.valueOf(true)))
                        .uri("lb://user-service"))
                .build();
    }
}