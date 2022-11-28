package com.example.restaurantsfoodwebsite.dto.productCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProductCategoryDto {

    private String name;
}
