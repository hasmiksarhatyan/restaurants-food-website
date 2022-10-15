package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.restaurantCategory.CreateRestaurantCategoryDto;
import com.example.restaurantsfoodwebsite.dto.restaurantCategory.RestaurantCategoryOverview;

import java.util.List;

public interface RestaurantCategoryService {

    List<RestaurantCategoryOverview> findAll();

    void addRestaurantCategory(CreateRestaurantCategoryDto dto);

    void deleteRestaurantCategory(int id);
}
