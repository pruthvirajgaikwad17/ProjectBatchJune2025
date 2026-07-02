package com.example.UserAuthService_June2025.controllers;

import com.example.UserAuthService_June2025.dtos.LoginRequestDto;
import com.example.UserAuthService_June2025.dtos.SignupRequestDto;
import com.example.UserAuthService_June2025.dtos.UserDto;
import com.example.UserAuthService_June2025.dtos.ValidateTokenRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // signup

    // login

    // validation token

    // logout

    // forget password

    // Return userDto because we dont want to send all the data of the user
    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        return null;
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return null;
    }

    @PostMapping("validateToken")
    public Boolean validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) {
        return null;
    }
}
