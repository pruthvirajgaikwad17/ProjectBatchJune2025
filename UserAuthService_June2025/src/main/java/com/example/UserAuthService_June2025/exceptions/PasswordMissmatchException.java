package com.example.UserAuthService_June2025.exceptions;

public class PasswordMissmatchException extends RuntimeException {
    public PasswordMissmatchException(String message) {
        super(message);
    }
}
