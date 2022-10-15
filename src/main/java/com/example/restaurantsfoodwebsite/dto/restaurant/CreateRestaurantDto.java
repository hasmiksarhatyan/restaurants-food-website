package com.example.restaurantsfoodwebsite.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantDto {

    private String name;
    private String address;
    private String email;
    private String phone;
    private Integer restaurantCategoryId;
    private Double deliveryPrice;
    private List<String> pictures;
}