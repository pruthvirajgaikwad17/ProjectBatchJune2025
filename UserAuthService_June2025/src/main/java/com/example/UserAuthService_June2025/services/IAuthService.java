package com.example.UserAuthService_June2025.services;

import com.example.UserAuthService_June2025.models.User;

public interface IAuthService {
    User signup(String name, String email, String password, String phoneNumber);

    User login(String email, String password);

    Boolean validateToken(String token, Long userId);
}
