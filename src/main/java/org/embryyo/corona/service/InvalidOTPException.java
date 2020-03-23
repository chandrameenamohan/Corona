package org.embryyo.corona.service;


public class InvalidOTPException extends RuntimeException {
    public InvalidOTPException(String msg) {
        super (msg);
    }
}
