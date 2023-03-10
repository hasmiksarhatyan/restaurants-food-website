package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmailIgnoreCase(String email);

    Optional<User> findByEmail(String email);
}
