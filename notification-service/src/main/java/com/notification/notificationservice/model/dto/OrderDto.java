package com.notification.notificationservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    private Integer id;
    private Integer customerId;
    private Integer orderId;
    private String orderName;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int orderCount;
}
