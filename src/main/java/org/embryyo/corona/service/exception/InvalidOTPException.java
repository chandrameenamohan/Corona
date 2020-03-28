package org.embryyo.corona.service.exception;


public class InvalidOTPException extends RuntimeException {
    public InvalidOTPException(String msg) {
        super (msg);
    }
}
