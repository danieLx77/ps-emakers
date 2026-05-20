package br.com.emakers.psemakers.exception.general;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public record RestErrorMessage(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message
) {
    public RestErrorMessage(HttpStatus status, String message) {
        this(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );
    }
}
