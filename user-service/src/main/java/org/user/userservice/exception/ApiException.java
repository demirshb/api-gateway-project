package org.user.userservice.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final String message;

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

}
