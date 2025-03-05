package com.parking.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("lpr-service", r -> r.path("/lpr/**")
                        .uri("http://localhost:8081"))
                .route("payment-service", r -> r.path("/payment/**")
                        .uri("http://localhost:9090"))
                .route("gate-service", r -> r.path("/gate/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}