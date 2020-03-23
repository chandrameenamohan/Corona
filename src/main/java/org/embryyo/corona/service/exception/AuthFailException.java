package org.embryyo.corona.service.exception;

public class AuthFailException extends RuntimeException {
    public AuthFailException(String msg) {
        super(msg);
    }
}
