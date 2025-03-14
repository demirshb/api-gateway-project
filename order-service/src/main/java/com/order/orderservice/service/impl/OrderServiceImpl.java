package com.order.orderservice.service.impl;


import com.order.orderservice.config.Properties;
import com.order.orderservice.model.dto.KafkaEventDto;
import com.order.orderservice.model.entity.Order;
import com.order.orderservice.model.enums.Event;
import com.order.orderservice.model.request.CreateOrderRequest;
import com.order.orderservice.repository.OrderRepository;
import com.order.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, KafkaEventDto> orderKafkaTemplate;
    private final Properties properties;

    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest, Integer clientId) {
        Order order = Order.builder()
                .orderCount(createOrderRequest.getOrderCount())
                .productName(createOrderRequest.getProductName())
                .customerId(clientId).build();
        Order savedOrder = orderRepository.save(order);
        KafkaEventDto event = KafkaEventDto.builder()
                .eventType(Event.ORDER_CREATED)
                .orderId(savedOrder.getId())
                .productName(savedOrder.getProductName())
                .customerId(clientId)
                .build();
        orderKafkaTemplate.send(properties.getTopicName(), event);
        return savedOrder;
    }

    @Override
    public Order getOrder(Integer orderId, int clientId) {
        return orderRepository.findByIdAndCustomerId(orderId, clientId);
    }
}