package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.basket.BasketOverview;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BasketService {

    Page<BasketOverview> getBaskets(Pageable pageable);

    void addBasket(int id, CurrentUser currentUser);

    void delete(int id);
}


