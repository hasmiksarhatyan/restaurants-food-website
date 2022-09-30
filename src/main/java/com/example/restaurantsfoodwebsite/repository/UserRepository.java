package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
