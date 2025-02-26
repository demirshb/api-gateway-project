package com.order.orderservice.service.impl;


import com.order.orderservice.model.entity.Order;
import com.order.orderservice.model.request.CreateOrderRequest;
import com.order.orderservice.repository.OrderRepository;
import com.order.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest, Integer clientId) {
        Order order = Order.builder()
                .orderCount(createOrderRequest.getOrderCount())
                .orderName(createOrderRequest.getOrderName())
                .customerId(clientId).build();
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Integer orderId, int clientId) {
        return orderRepository.findByIdAndCustomerId(orderId, clientId);
    }
}