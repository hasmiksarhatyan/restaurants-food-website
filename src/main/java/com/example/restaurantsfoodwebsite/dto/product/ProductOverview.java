package com.example.restaurantsfoodwebsite.dto.product;

import com.example.restaurantsfoodwebsite.dto.productCategory.ProductCategoryOverview;
import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import com.example.restaurantsfoodwebsite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOverview {

    private int id;
    private String name;
    private String description;
    private Double price;
    private ProductCategoryOverview productCategoryOverview;
    private RestaurantOverview restaurantOverview;
    private List<String> pictures;
    private User user;
}