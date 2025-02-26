package com.order.orderservice.repository;

import com.order.orderservice.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByIdAndCustomerId(Integer orderId, int clientId);
}