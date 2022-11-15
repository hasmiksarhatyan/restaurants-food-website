package com.example.restaurantsfoodwebsite.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProductDto {

    private String name;
    private String description;
    private Double price;
    private Integer productCategoryId;
    private Integer restaurantId;
    private List<String> pictures;


}

