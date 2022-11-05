package com.example.restaurantsfoodwebsite.dto.product;

import com.example.restaurantsfoodwebsite.entity.ProductCategory;
import com.example.restaurantsfoodwebsite.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private Double price;
    private ProductCategory productCategory;
    private Restaurant restaurant;
    private List<String> pictures;


}

