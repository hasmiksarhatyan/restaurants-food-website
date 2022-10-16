package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.restaurantCategory.CreateRestaurantCategoryDto;
import com.example.restaurantsfoodwebsite.dto.restaurantCategory.RestaurantCategoryOverview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantCategoryService {

    Page<RestaurantCategoryOverview> findAll(Pageable pageable);

    List<RestaurantCategoryOverview> findAll();

    void addRestaurantCategory(CreateRestaurantCategoryDto dto);

    void deleteRestaurantCategory(int id);
}
