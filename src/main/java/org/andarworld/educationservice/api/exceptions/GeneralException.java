package org.andarworld.educationservice.api.exceptions;

public class GeneralException extends RuntimeException {
    private Integer statusCode;

    public GeneralException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}