package org.gayeway.gatewayservice.config.filter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.gayeway.gatewayservice.config.Constants;
import org.gayeway.gatewayservice.service.JwtService;
import org.gayeway.gatewayservice.service.RedisServiceImpl;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class OrderServiceFilter extends AbstractGatewayFilterFactory<OrderServiceFilter.Config> {

    private final JwtService jwtService;
    private final RedisServiceImpl redisService;


    public OrderServiceFilter(JwtService jwtService, RedisServiceImpl redisService) {
        super(Config.class);
        this.jwtService = jwtService;
        this.redisService = redisService;
    }

    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            try {

                final List<String> authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
                if (!CollectionUtils.isEmpty(authHeaders)) {
                    String token = authHeaders.get(0).split(" ")[1];
                    if(!redisService.isTokenAlive(token))
                        return this.onError(exchange, HttpStatus.UNAUTHORIZED);
                    Claims claims = jwtService.extractAllClaims(token);
                    String clientId = claims.get(Constants.Api.CLIENT_ID).toString();
                    ServerHttpRequest request = exchange.getRequest().mutate()
                            .header(Constants.Api.CLIENT_ID, clientId)
                            .build();
                    log.debug(claims.toString());
                    return chain.filter(exchange.mutate().request(request).build());
                } else {
                    log.error("Header must have Authorization header!");
                    return this.onError(exchange, HttpStatus.UNAUTHORIZED);
                }
            } catch (Exception e) {
                log.error("{}", e.getMessage());
                log.error("Invalid token!");
                return this.onError(exchange, HttpStatus.UNAUTHORIZED);
            }
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    public static class Config {
    }
}
