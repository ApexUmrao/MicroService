package com.apex.apigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApigatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigatewayserverApplication.class, args);
    }

    public RouteLocator  getRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/apexbank/accounts/**")
                        .filters( f -> f.rewritePath("/apexbank/accounts/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("accountsCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://ACCOUNTS"))
                .route(p -> p
                        .path("/apexbank/loans/**")
                        .filters( f -> f.rewritePath("/apexbank/loans/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("loansCircuitBreaker")
                                         .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://LOANS"))
                .route(p -> p
                        .path("/apexbank/cards/**")
                        .filters( f -> f.rewritePath("/apexbank/cards/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("cardsCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport")))
                        .uri("lb://CARDS")).build();



    }
}
