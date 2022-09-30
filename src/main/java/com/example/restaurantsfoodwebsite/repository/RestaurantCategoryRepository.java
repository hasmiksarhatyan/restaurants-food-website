package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.RestaurantCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Integer> {
}
