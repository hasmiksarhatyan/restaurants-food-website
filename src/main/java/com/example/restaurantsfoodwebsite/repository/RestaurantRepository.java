package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.Restaurant;
import com.example.restaurantsfoodwebsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    boolean existsByEmailIgnoreCase(String email);

    Page<Restaurant> findRestaurantsByUser(User user, Pageable pageable);

    Optional<Restaurant> findRestaurantsByUser(User user);

//    Restaurant getReferenceById(int id);
}
