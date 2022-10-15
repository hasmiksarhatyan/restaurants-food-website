package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    Optional<VerificationToken> findByPlainToken(String token);
}

