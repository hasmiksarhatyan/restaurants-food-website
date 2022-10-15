package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.restaurant.CreateRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import com.example.restaurantsfoodwebsite.entity.Restaurant;
import com.example.restaurantsfoodwebsite.mapper.RestaurantMapper;
import com.example.restaurantsfoodwebsite.repository.RestaurantRepository;
import com.example.restaurantsfoodwebsite.service.RestaurantService;
import com.example.restaurantsfoodwebsite.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final FileUtil fileUtil;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;


    @Override
    public List<RestaurantOverview> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantOverview> restaurantOverviews = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantOverviews.add(restaurantMapper.mapToOverview(restaurant));
        }
        return restaurantOverviews;
    }

    @Override
    public void addRestaurant(CreateRestaurantDto dto, MultipartFile[] files) throws IOException {
        if (restaurantRepository.existsByEmailIgnoreCase(dto.getEmail())) {
            throw new IllegalStateException();
        }
        dto.setPictures(fileUtil.uploadImages(files));
        restaurantRepository.save(restaurantMapper.mapToEntity(dto));
    }

    @Override
    public byte[] getRestaurantImage(String fileName) throws IOException {
        return fileUtil.getImage(fileName);
    }

    @Override
    public void deleteRestaurant(int id) {
        if (!restaurantRepository.existsById(id)) {
            throw new IllegalStateException();
        }
        restaurantRepository.deleteById(id);
    }
}
