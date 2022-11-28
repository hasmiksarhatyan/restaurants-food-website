package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.order.OrderOverview;
import com.example.restaurantsfoodwebsite.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    Page<OrderOverview> getOrders(Pageable pageable);

    Order addOrder(Order order);

    void delete(int id);
}


