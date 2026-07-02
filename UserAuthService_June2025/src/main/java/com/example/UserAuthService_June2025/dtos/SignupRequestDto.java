package com.example.UserAuthService_June2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String name;

    private String email;

    private String password;

    private String phoneNumber;
}
