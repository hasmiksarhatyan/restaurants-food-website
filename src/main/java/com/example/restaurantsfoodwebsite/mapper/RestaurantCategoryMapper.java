package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.restaurantCategory.CreateRestaurantCategoryDto;
import com.example.restaurantsfoodwebsite.dto.restaurantCategory.RestaurantCategoryOverview;
import com.example.restaurantsfoodwebsite.entity.RestaurantCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantCategoryMapper {

    public RestaurantCategoryOverview mapToOverview(RestaurantCategory restaurantCategory) {
        return RestaurantCategoryOverview.builder()
                .id(restaurantCategory.getId())
                .name(restaurantCategory.getName())
                .build();
    }

    public RestaurantCategory mapToEntity(CreateRestaurantCategoryDto dto) {
        return RestaurantCategory.builder()
                .name(dto.getName())
                .build();
    }
}

