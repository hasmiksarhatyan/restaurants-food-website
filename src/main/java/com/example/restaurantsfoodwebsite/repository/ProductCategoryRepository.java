package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
