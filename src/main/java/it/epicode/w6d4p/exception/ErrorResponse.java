package it.epicode.w6d4p.exception;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorResponse {
    private String message;
    private LocalDateTime dataError;

    public ErrorResponse(String message) {
        this.message = message;
        dataError = LocalDateTime.now();
    }
}
