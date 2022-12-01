package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.product.CreateProductDto;
import com.example.restaurantsfoodwebsite.dto.product.ProductOverview;
import com.example.restaurantsfoodwebsite.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "dto.restaurantId", target = "restaurant.id")
    @Mapping(source = "dto.productCategoryId", target = "productCategory.id")
    Product mapToEntity(CreateProductDto dto);

    @Mapping(source = "product.restaurant", target = "restaurantOverview")
    @Mapping(source = "product.productCategory", target = "productCategoryOverview")
    @Mapping(source = "product.user", target = "userOverview")
    ProductOverview mapToOverview(Product product);

    List<ProductOverview> mapToOverviewList(List<Product> products);
}
