package com.example.UserAuthService_June2025.exceptions;


public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String message) {
        super(message);
    }
}
