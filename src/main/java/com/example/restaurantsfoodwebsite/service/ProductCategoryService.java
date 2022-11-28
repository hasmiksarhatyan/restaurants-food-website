package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.productCategory.CreateProductCategoryDto;
import com.example.restaurantsfoodwebsite.dto.productCategory.EditProductCategoryDto;
import com.example.restaurantsfoodwebsite.dto.productCategory.ProductCategoryOverview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService {
    Page<ProductCategoryOverview> findAll(Pageable pageable);

    List<ProductCategoryOverview> findAll();

    void addProductCategory(CreateProductCategoryDto dto);

    void editProductCategory(EditProductCategoryDto dto, int id);

    void deleteProductCategory(int id);

    ProductCategoryOverview findById(int id);

}
