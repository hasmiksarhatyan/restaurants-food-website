package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.Basket;
import com.example.restaurantsfoodwebsite.entity.Product;
import com.example.restaurantsfoodwebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

    boolean existsByProduct(Product product);


    Basket findByProductAndUser(Product product, User user);

    Optional<Basket> findByUser(User user);
}
