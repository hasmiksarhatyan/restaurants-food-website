package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.productCategory.ProductCategoryDto;
import com.example.restaurantsfoodwebsite.dto.productCategory.ProductCategoryOverview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService {
    Page<ProductCategoryOverview> findAll(Pageable pageable);

    List<ProductCategoryOverview> findAll();

    void addProductCategory(ProductCategoryDto dto);

    void editProductCategory(ProductCategoryDto dto, int id);

    void deleteProductCategory(int id);

    ProductCategoryOverview findById(int id);

}
