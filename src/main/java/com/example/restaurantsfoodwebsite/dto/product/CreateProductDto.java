package com.example.restaurantsfoodwebsite.dto.product;

import com.example.restaurantsfoodwebsite.dto.productCategory.ProductCategoryOverview;
import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import com.example.restaurantsfoodwebsite.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {

    private String name;
    private String description;
    private Double price;
    private Integer productCategoryId;
    private Integer restaurantId;
    private List<String> pictures;


}

