package org.user.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.user.userservice.model.dto.CustomerDto;

public interface UserService {
    UserDetailsService userDetailsService();

    CustomerDto getCustomer(Integer customerId);
}
