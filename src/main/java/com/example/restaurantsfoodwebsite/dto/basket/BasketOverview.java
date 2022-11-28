package com.example.restaurantsfoodwebsite.dto.basket;

import com.example.restaurantsfoodwebsite.entity.Product;
import com.example.restaurantsfoodwebsite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketOverview {

    private Integer id;
    private double quantity;
    private Product product;
    private User user;
}
