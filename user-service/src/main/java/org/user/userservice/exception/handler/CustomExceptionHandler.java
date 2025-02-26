package org.user.userservice.exception.handler;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.user.userservice.config.Constants;
import org.user.userservice.exception.InvalidRoleException;
import org.user.userservice.exception.UserDoesNotExistException;
import org.user.userservice.exception.UserExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> handleException(Exception e) {
        log.error("An unknown exception occurred!", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(generateError(Constants.ErrorCode.INTERNAL_ERROR, e.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Error> handleException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        if (!allErrors.isEmpty()) {
            String defaultMessage = allErrors.get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(generateError(Constants.ErrorCode.VALIDATION_ERROR, defaultMessage));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(generateError(Constants.ErrorCode.VALIDATION_ERROR, Constants.ErrorMessage.VALIDATION_ERROR));
    }


    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Error> handleException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(generateError(e.getStatusCode().value(), e.getMessage()));
    }

    @ExceptionHandler({UserExistException.class})
    public ResponseEntity<Error> handleException(UserExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(generateError(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<Error> handleException(UserDoesNotExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(generateError(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler({InvalidRoleException.class})
    public ResponseEntity<Error> handleException(InvalidRoleException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(generateError(e.getCode(), e.getMessage()));
    }


    private Error generateError(int code, String message) {
        return Error.builder()
                .code(code)
                .message(message)
                .timestamp(LocalDate.now())
                .build();
    }
}
