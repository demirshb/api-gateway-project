package org.user.userservice.service.impl;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.user.userservice.model.entity.Customer;
import org.user.userservice.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.user.userservice.repository.CustomerRepository;
import org.user.userservice.service.UserService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    @PostConstruct
    private void postConstruct() {
        Customer user = new Customer();

        user.setName("admin");
        user.setSurname("admin");
        user.setEmail("admin@abc.com");
        user.setCreditLimit(new BigDecimal("1000000"));
        user.setUsedCreditLimit(BigDecimal.ZERO);
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setRole(Role.ADMIN);

        customerRepository.save(user);
    }
}