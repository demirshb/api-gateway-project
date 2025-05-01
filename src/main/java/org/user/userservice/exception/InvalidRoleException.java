package org.user.userservice.exception;

import org.user.userservice.config.Constants;
import lombok.Getter;

@Getter
public class InvalidRoleException extends ApiException {
    private final int code;

    public InvalidRoleException() {
        super(Constants.ErrorMessage.INVALID_ROLE);
        this.code = Constants.ErrorCode.INVALID_ROLE;
    }
}
