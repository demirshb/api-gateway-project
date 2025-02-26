package com.order.orderservice.controller;

import com.order.orderservice.config.Constants;
import com.order.orderservice.model.entity.Order;
import com.order.orderservice.model.request.CreateOrderRequest;
import com.order.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<Order> register(@RequestBody CreateOrderRequest createOrderRequest,
                                          @RequestHeader(name = Constants.Api.CLIENT_ID, required = false) String clientId) {
        return ResponseEntity.ok(orderService.createOrder(createOrderRequest, Integer.parseInt(clientId)));
    }

    @GetMapping("/get-order")
    public ResponseEntity<Order> getOrder(@RequestParam(name = "orderId") Integer orderId,
                                          @RequestHeader(name = Constants.Api.CLIENT_ID, required = false) String clientId) {
        return ResponseEntity.ok(orderService.getOrder(orderId, Integer.parseInt(clientId)));
    }

}