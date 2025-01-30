package com.example.backend.exception;

public class StudentAccessException extends RuntimeException {
    public StudentAccessException(String message) {
        super(message);
    }
}
