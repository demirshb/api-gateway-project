package com.notification.notificationservice.model.dto;

import com.notification.notificationservice.model.enums.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaEventDto {
    private int customerId;
    private int orderId;
    private String productName;
    private Event eventType;
}
