package com.notification.notificationservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Event {
    ORDER_CREATED("CREATED");

    private final String eventType;

    public static Event fromValue(String eventType) {
        for (Event r : values()) {
            if (Objects.equals(eventType, r.getEventType())) {
                return r;
            }
        }
        throw new RuntimeException("Invalid Event: ");
    }
}
