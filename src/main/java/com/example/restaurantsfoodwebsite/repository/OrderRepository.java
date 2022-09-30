package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
