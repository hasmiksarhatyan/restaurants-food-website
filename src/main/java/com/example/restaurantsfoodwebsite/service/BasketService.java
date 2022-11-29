package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.basket.BasketOverview;
import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BasketService {

    Page<BasketOverview> getBaskets(Pageable pageable, User user);

    void addBasket(int id, CurrentUser currentUser);

    void delete(int id);
}


