package com.order.orderservice.model.dto;

import com.order.orderservice.model.enums.Event;
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
