package com.example.UserAuthService_June2025.services;

import com.example.UserAuthService_June2025.dtos.SendEmailDto;
import com.example.UserAuthService_June2025.exceptions.PasswordMissmatchException;
import com.example.UserAuthService_June2025.exceptions.UserAlreadyExistException;
import com.example.UserAuthService_June2025.exceptions.UserNotSignedUpException;
import com.example.UserAuthService_June2025.models.Session;
import com.example.UserAuthService_June2025.models.SessionState;
import com.example.UserAuthService_June2025.models.Status;
import com.example.UserAuthService_June2025.models.User;
import com.example.UserAuthService_June2025.repos.SessionRepo;
import com.example.UserAuthService_June2025.repos.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private SecretKey secretKey;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public User signup(String name, String email, String password, String phoneNumber) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if(userOptional.isPresent()) {
            throw new UserAlreadyExistException("Please login directly");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setName(name);
        user.setPhoneNumber(phoneNumber);

        user = userRepo.save(user);

        // Before returning to user push a message to kafka to send a welcome email

        SendEmailDto emailDto = new SendEmailDto();

        emailDto.setEmail(email);
        emailDto.setSubject("Welcome to scaler!");
        emailDto.setBody("Welcome and new registration is done.");
        kafkaTemplate.send("sendWelcomeEmail", objectMapper.writeValueAsString(emailDto));

        return user;
    }

    @Override
    public Pair<User, String> login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if(userOptional.isEmpty()) {
            throw new UserNotSignedUpException("Please create your account first");
        }

        User user = userOptional.get();

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new PasswordMissmatchException("Password Didn't match");
        }

        // Generate JWT token

//        String message = "{\n" +
//                "    \"email\": \"anurag@gmail.com\",\n" +
//                "    \"roles\": [\n" +
//                "        \"instructor\",\n" +
//                "        \"buddy\"\n" +
//                "    ],\n" +
//                "    \"expirationDate\": \"2ndApril2026\"\n" +
//                "}";
//
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("iss", "scaler");
        claims.put("gen", System.currentTimeMillis());
        claims.put("exp",System.currentTimeMillis()+100000);
        claims.put("scope", user.getRoleList());

//        MacAlgorithm algorithm = Jwts.SIG.HS256;
//        SecretKey secretKey = algorithm.key().build();
        String token = Jwts.builder()
                .claims(claims)
                .signWith(secretKey)
                .compact();

        Session session = new Session();
        session.setUser(user);
        session.setToken(token);
        session.setState(SessionState.ACTIVE);
        sessionRepo.save(session);

        return new Pair<User, String>(user, token);
    }

    @Override
    public Boolean validateToken(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepo.findByTokenAndUser_Id(token, userId);

        if(sessionOptional.isEmpty()) {
            return false;
        }

        Session session = sessionOptional.get();
        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();

        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        Long expiry = (Long)claims.get("exp");
        Long currentTime = System.currentTimeMillis();

        System.out.println("token expiry = "+expiry);
        System.out.println("current time = "+currentTime);

        if(currentTime > expiry) {
            session.setState(SessionState.EXPERIED);
            sessionRepo.save(session);
            return false;
        }

        return true;
    }
}
