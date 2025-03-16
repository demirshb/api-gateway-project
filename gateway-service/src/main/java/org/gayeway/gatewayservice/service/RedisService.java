package org.gayeway.gatewayservice.service;

public interface RedisService {
    boolean isTokenAlive(String key);
}
