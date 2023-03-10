package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.productCategory.CreateProductCategoryDto;
import com.example.restaurantsfoodwebsite.dto.productCategory.ProductCategoryOverview;
import com.example.restaurantsfoodwebsite.entity.ProductCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    ProductCategory mapToEntity(CreateProductCategoryDto productCategoryDto);

    ProductCategoryOverview mapToOverview(ProductCategory productCategory);

    List<ProductCategoryOverview> mapToOverviewList(List<ProductCategory> productCategories);


}
