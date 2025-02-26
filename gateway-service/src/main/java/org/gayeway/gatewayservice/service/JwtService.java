package org.gayeway.gatewayservice.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    public Claims extractAllClaims(String token);

}
