package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.restaurantCategory.CreateRestaurantCategoryDto;
import com.example.restaurantsfoodwebsite.dto.restaurantCategory.RestaurantCategoryOverview;
import com.example.restaurantsfoodwebsite.entity.RestaurantCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public Page<RestaurantCategoryOverview> mapToOverviewPage(Page<RestaurantCategory> categories, Pageable pageable) {
        List<RestaurantCategoryOverview> categoryOverviews = new ArrayList<>();
        for (RestaurantCategory category : categories) {
            categoryOverviews.add(mapToOverview(category));
        }
        return new PageImpl<>(categoryOverviews, pageable, categoryOverviews.size());
    }
}

