package org.user.userservice.model.enums;

import org.user.userservice.exception.InvalidRoleException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private final String role;

    public static Role fromValue(String role) {
        for (Role r : values()) {
            if (Objects.equals(role, r.getRole())) {
                return r;
            }
        }
        throw new InvalidRoleException();
    }
}
