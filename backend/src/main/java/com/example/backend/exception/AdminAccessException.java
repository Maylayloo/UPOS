package com.example.backend.exception;

public class AdminAccessException extends RuntimeException {
    public AdminAccessException(String message) {
        super(message);
    }
}
