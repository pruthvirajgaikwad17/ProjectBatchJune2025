package com.example.UserAuthService_June2025.repos;

import com.example.UserAuthService_June2025.models.Session;
import com.example.UserAuthService_June2025.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {
    Optional<Session> findByTokenAndUser_Id(String s, Long userId);
}
