package com.SpringBoot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String error;
    private String message;
    private String details;
    private LocalDateTime timestamp;
}
