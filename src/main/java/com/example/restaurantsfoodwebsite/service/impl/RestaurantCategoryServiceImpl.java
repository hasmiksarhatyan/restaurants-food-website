package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.restaurantCategory.CreateRestaurantCategoryDto;
import com.example.restaurantsfoodwebsite.dto.restaurantCategory.RestaurantCategoryOverview;
import com.example.restaurantsfoodwebsite.entity.RestaurantCategory;
import com.example.restaurantsfoodwebsite.mapper.RestaurantCategoryMapper;
import com.example.restaurantsfoodwebsite.repository.RestaurantCategoryRepository;
import com.example.restaurantsfoodwebsite.service.RestaurantCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantCategoryServiceImpl implements RestaurantCategoryService {

    private final RestaurantCategoryMapper categoryMapper;
    private final RestaurantCategoryRepository restaurantCategoryRepository;

    @Override
    public List<RestaurantCategoryOverview> findAll() {
        List<RestaurantCategory> categories = restaurantCategoryRepository.findAll();
        List<RestaurantCategoryOverview> categoriesOverviews = new ArrayList<>();
        for (RestaurantCategory category : categories) {
            categoriesOverviews.add(categoryMapper.mapToOverview(category));
        }
        return categoriesOverviews;
    }

    @Override
    public void addRestaurantCategory(CreateRestaurantCategoryDto dto) {
        restaurantCategoryRepository.save(categoryMapper.mapToEntity(dto));
    }

    @Override
    public void deleteRestaurantCategory(int id) {
        if (!restaurantCategoryRepository.existsById(id)) {
            throw new IllegalStateException();
        }
        restaurantCategoryRepository.deleteById(id);
    }
}
