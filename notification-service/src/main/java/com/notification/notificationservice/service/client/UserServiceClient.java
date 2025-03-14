package com.notification.notificationservice.service.client;

import com.notification.notificationservice.model.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", url = "${client.user-service}")
public interface UserServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/internal/customer")
    CustomerDto getCustomer(@RequestParam("id") int customerId);
}
