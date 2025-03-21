package org.user.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.user.userservice.exception.UserDoesNotExistException;
import org.user.userservice.exception.UserExistException;
import org.user.userservice.model.entity.Customer;
import org.user.userservice.model.enums.Role;
import org.user.userservice.model.request.LoginRequest;
import org.user.userservice.model.request.RegisterRequest;
import org.user.userservice.model.response.CreateCustomerResponse;
import org.user.userservice.model.response.JwtAuthenticationResponse;
import org.user.userservice.repository.CustomerRepository;
import org.user.userservice.service.AuthenticationService;
import org.user.userservice.service.JwtService;
import org.user.userservice.service.RedisService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RedisService redisService;
    @Value("${jwt.expiration}")
    private long expiration;

    public CreateCustomerResponse register(RegisterRequest registerRequest) {
        Optional<Customer> existingUser = customerRepository.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserExistException();
        }
        Customer user = Customer.builder()
                .name(registerRequest.getFirstName())
                .surname(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .creditLimit(new BigDecimal("1000000"))
                .usedCreditLimit(BigDecimal.ZERO)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.CUSTOMER).build();
        Customer createdCustomer = customerRepository.save(user);
        return CreateCustomerResponse.builder()
                .customerId(createdCustomer.getId()).build();
    }

    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword())
        );

        var user = customerRepository.findByEmail(loginRequest.getEmail()).orElseThrow(UserDoesNotExistException::new);
        var jwt = jwtService.generateToken(user);
        redisService.redisSaveToken(jwt, jwt, expiration, TimeUnit.HOURS);
        return JwtAuthenticationResponse.builder()
                .token(jwt).build();
    }

}