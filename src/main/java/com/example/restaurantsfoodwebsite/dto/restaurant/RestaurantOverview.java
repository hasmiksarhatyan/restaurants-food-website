package com.example.restaurantsfoodwebsite.dto.restaurant;

import com.example.restaurantsfoodwebsite.dto.restaurantCategory.RestaurantCategoryOverview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOverview {

    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private RestaurantCategoryOverview RestaurantCategoryOverview;
    private Double deliveryPrice;
    private List<String> pictures;
}