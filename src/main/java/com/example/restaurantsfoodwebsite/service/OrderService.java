package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.order.CreateOrderDto;
import com.example.restaurantsfoodwebsite.dto.order.OrderOverview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    Page<OrderOverview> getOrders(Pageable pageable);

    void addOrder(CreateOrderDto dto);

    void delete(int id);
}


