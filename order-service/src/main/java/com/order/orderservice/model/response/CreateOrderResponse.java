package com.order.orderservice.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateOrderResponse {
    private Integer orderId;
}