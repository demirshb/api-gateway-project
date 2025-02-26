package org.user.userservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.user.userservice.model.entity.Customer;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(Customer userDetails);

    Boolean validateToken(String token, UserDetails userDetails);

}
