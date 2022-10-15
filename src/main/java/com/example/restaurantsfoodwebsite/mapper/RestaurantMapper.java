package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.restaurant.CreateRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import com.example.restaurantsfoodwebsite.entity.Restaurant;
import com.example.restaurantsfoodwebsite.repository.RestaurantCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

    private final RestaurantCategoryRepository restaurantCategoryRepository;
    private final RestaurantCategoryMapper restaurantCategoryMapper;


    public RestaurantOverview mapToOverview(Restaurant restaurant) {
        return RestaurantOverview.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .email(restaurant.getEmail())
                .phone(restaurant.getPhone())
                .address(restaurant.getAddress())
                .deliveryPrice(restaurant.getDeliveryPrice())
                .pictures(restaurant.getPictures())
                .RestaurantCategoryOverview(restaurantCategoryMapper.mapToOverview(restaurant.getRestaurantCategory()))
                .build();
    }

    public Restaurant mapToEntity(CreateRestaurantDto dto) {
        return Restaurant.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .restaurantCategory(restaurantCategoryRepository.getReferenceById(dto.getRestaurantCategoryId()))
                .deliveryPrice(dto.getDeliveryPrice())
                .pictures(dto.getPictures())
                .build();
    }
}

