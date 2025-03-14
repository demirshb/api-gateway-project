package com.order.orderservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Properties {

    @Value("${topic.name}")
    private String topicName;
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
}
