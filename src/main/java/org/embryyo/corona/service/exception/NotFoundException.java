package org.embryyo.corona.service.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super (msg);
    }
}
