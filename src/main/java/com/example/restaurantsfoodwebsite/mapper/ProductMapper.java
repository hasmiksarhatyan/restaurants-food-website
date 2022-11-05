package com.example.restaurantsfoodwebsite.mapper;

import com.example.restaurantsfoodwebsite.dto.product.ProductDto;
import com.example.restaurantsfoodwebsite.dto.product.ProductOverview;
import com.example.restaurantsfoodwebsite.entity.Product;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapToEntity(ProductDto dto, @AuthenticationPrincipal CurrentUser currentUser);

    @Mapping(source = "product.restaurant", target = "restaurantOverview")
    @Mapping(source = "product.productCategory", target = "productCategoryOverview")
    ProductOverview mapToOverview(Product product);

    List<ProductOverview> mapToOverviewList(List<Product> products);
}
