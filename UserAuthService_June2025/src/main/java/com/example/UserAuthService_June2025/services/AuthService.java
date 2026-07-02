package com.example.UserAuthService_June2025.services;

import com.example.UserAuthService_June2025.models.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{

    @Override
    public User signup(String name, String email, String password, String phoneNumber) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public Boolean validateToken(String token, Long userId) {
        return null;
    }
}
