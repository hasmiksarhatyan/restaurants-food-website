package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.restaurant.CreateRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import com.example.restaurantsfoodwebsite.entity.Restaurant;
import com.example.restaurantsfoodwebsite.entity.Role;
import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.repository.RestaurantCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                .user(restaurant.getUser())
                .build();
    }

    public Restaurant mapToEntity(CreateRestaurantDto dto, User user) {
        if (user.getRole() == Role.MANAGER) {
            user.setRole(Role.MANAGER);
        }
        user.setRole(Role.RESTAURANT_OWNER);
        return Restaurant.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .restaurantCategory(restaurantCategoryRepository.getReferenceById(dto.getRestaurantCategoryId()))
                .deliveryPrice(dto.getDeliveryPrice())
                .pictures(dto.getPictures())
                .user(user)
                .build();
    }


    public Page<RestaurantOverview> mapToOverviewPage(Page<Restaurant> allRestaurants, Pageable pageable) {
        List<RestaurantOverview> restaurantOverviews = new ArrayList<>();
        for (Restaurant restaurant : allRestaurants) {
            restaurantOverviews.add(mapToOverview(restaurant));
        }
        return new PageImpl<>(restaurantOverviews, pageable, restaurantOverviews.size());
    }

    public List<RestaurantOverview> mapToOverviewList(List<Restaurant> all) {
        List<RestaurantOverview> restaurantOverviews = new ArrayList<>();
        for (Restaurant restaurant : all) {
            restaurantOverviews.add(mapToOverview(restaurant));
        }
        return restaurantOverviews;
    }
}

