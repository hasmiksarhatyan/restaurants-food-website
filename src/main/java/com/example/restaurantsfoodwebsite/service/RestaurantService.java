package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.restaurant.CreateRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RestaurantService {

    List<RestaurantOverview> findAllRestaurants();

    void addRestaurant(CreateRestaurantDto restaurantDto, MultipartFile[] files) throws IOException;

    byte[] getRestaurantImage(String fileName) throws IOException;


    void deleteRestaurant(int id);

}