package com.order.orderservice.service;


import com.order.orderservice.model.entity.Order;
import com.order.orderservice.model.request.CreateOrderRequest;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderService {

    Order createOrder(CreateOrderRequest createOrderRequest, Integer clientId);

    Order getOrder(Integer orderId, int clientId);
}
