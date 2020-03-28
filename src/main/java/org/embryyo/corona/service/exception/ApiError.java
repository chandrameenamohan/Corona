package org.embryyo.corona.service.exception;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ApiError {

    private HttpStatus httpStatus;
    private int devCode;
    private String devMessage;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getDevCode() {
        return devCode;
    }

    public void setDevCode(int devCode) {
        this.devCode = devCode;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiError apiError = (ApiError) o;
        return devCode == apiError.devCode &&
                httpStatus == apiError.httpStatus &&
                Objects.equals(devMessage, apiError.devMessage) &&
                Objects.equals(message, apiError.message) &&
                Objects.equals(errors, apiError.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus, devCode, devMessage, message, errors);
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "httpStatus=" + httpStatus +
                ", devCode=" + devCode +
                ", devMessage='" + devMessage + '\'' +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
