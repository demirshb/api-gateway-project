package org.user.userservice.exception;

import org.user.userservice.config.Constants;
import lombok.Getter;

@Getter
public class UserDoesNotExistException extends ApiException {
    private final int code;

    public UserDoesNotExistException() {
        super(Constants.ErrorMessage.CUSTOMER_DOES_NOT_EXIST);
        this.code = Constants.ErrorCode.CUSTOMER_DOES_NOT_EXIST;
    }
}
