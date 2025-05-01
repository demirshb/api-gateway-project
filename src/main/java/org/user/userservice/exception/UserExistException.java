package org.user.userservice.exception;

import org.user.userservice.config.Constants;
import lombok.Getter;


@Getter
public class UserExistException extends ApiException {
    private final int code;

    public UserExistException() {
        super(Constants.ErrorMessage.CUSTOMER_EXIST);
        this.code = Constants.ErrorCode.CUSTOMER_EXIST;
    }
}
