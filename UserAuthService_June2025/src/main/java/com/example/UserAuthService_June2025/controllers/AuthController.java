package com.example.UserAuthService_June2025.controllers;

import com.example.UserAuthService_June2025.dtos.LoginRequestDto;
import com.example.UserAuthService_June2025.dtos.SignupRequestDto;
import com.example.UserAuthService_June2025.dtos.UserDto;
import com.example.UserAuthService_June2025.dtos.ValidateTokenRequestDto;
import com.example.UserAuthService_June2025.models.User;
import com.example.UserAuthService_June2025.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    // signup

    // login

    // validation token

    // logout

    // forget password

    // Return userDto because we dont want to send all the data of the user
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        User user = authService.signup(signupRequestDto.getName(), signupRequestDto.getEmail(), signupRequestDto.getPassword(), signupRequestDto.getPhoneNumber());
        UserDto userDtoResponse = from(user);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        User user = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return new ResponseEntity<>(from(user), HttpStatus.OK);
    }

    @PostMapping("validateToken")
    public Boolean validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) {
        return null;
    }

    UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
