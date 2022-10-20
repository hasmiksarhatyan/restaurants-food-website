package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.restaurant.CreateRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.EditRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import com.example.restaurantsfoodwebsite.entity.Restaurant;
import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RestaurantService {

    Page<RestaurantOverview> findAllRestaurants(Pageable pageable);

    void addRestaurant(CreateRestaurantDto restaurantDto, MultipartFile[] files, CurrentUser currentUser) throws IOException;

    byte[] getRestaurantImage(String fileName) throws IOException;


    void deleteRestaurant(int id);

    Page<RestaurantOverview> getRestaurantsByUser(User user, Pageable pageable);

    void editRestaurant(EditRestaurantDto dto, int id);

    RestaurantOverview getRestaurant(int id);

    Restaurant findRestaurant(int id);
}


