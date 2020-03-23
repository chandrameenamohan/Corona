package org.embryyo.corona.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidOTPException.class)
    public ApiError handleInvalidOtpException(Exception ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthFailException.class)
    public ApiError handleAuthorizationFailException(Exception ex) {
        ApiError error = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiError handleNotFoundException(Exception ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return error;
    }
}
