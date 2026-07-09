package com.example.UserAuthService_June2025.exceptions;

public class UserNotSignedUpException extends RuntimeException {
    public UserNotSignedUpException(String message) {
        super(message);
    }
}
