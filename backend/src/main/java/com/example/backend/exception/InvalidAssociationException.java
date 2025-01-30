package com.example.backend.exception;

public class InvalidAssociationException extends RuntimeException {
    public InvalidAssociationException(String message) {
        super(message);
    }
}
