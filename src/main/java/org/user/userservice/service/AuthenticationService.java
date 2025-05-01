package org.user.userservice.service;


import org.user.userservice.model.request.LoginRequest;
import org.user.userservice.model.request.RegisterRequest;
import org.user.userservice.model.response.CreateCustomerResponse;
import org.user.userservice.model.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    CreateCustomerResponse register(RegisterRequest registerRequest);
    JwtAuthenticationResponse login(LoginRequest loginRequest);
}
