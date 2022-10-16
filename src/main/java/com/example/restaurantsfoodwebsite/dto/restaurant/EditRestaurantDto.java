package com.example.restaurantsfoodwebsite.dto.restaurant;

import com.example.restaurantsfoodwebsite.entity.RestaurantCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditRestaurantDto {

    private String name;
    private String address;
    private String email;
    private String phone;
    private RestaurantCategory restaurantCategory;
    private Double deliveryPrice;
    private List<String> pictures;
}
