package com.notification.notificationservice.service;

import com.notification.notificationservice.model.dto.CustomerDto;
import com.notification.notificationservice.model.dto.KafkaEventDto;
import com.notification.notificationservice.model.entity.Notification;
import com.notification.notificationservice.model.repository.NotificationRepository;
import com.notification.notificationservice.service.client.UserServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final UserServiceClient userServiceClient;
    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "${topic.name}")
    public void createOrderNotificationListener(KafkaEventDto message) {
        log.info("notification message received: {}", message);
        CustomerDto customer = userServiceClient.getCustomer(message.getCustomerId());
        Notification notification = Notification.builder()
                .customerId(customer.getId())
                .orderId(message.getOrderId())
                .productName(message.getProductName())
                .notificationType(message.getEventType().getEventType())
                .build();
        notificationRepository.save(notification);
    }

    public List<Notification> getAllNotification() {
        return notificationRepository.findAll();
    }
}
