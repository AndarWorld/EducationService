package org.andarworld.educationservice.api.exceptions;

public record ErrorResponse(
        String message,
        Integer statusCode,
        String date
) {
}
