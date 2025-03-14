package org.user.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.user.userservice.model.dto.CustomerDto;
import org.user.userservice.service.impl.UserServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/internal")
public class InternalController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/customer")
    public ResponseEntity<CustomerDto> getCustomer(@RequestParam(value = "id") Integer customerId) {
        return ResponseEntity.ok(userServiceImpl.getCustomer(customerId));
    }


}