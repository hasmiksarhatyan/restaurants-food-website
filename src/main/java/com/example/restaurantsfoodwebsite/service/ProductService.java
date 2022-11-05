package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.product.ProductDto;
import com.example.restaurantsfoodwebsite.dto.product.ProductOverview;
import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Page<ProductOverview> sortProduct(Pageable pageable, String sortProduct, Integer id);

    List<ProductOverview> findAll();

    void addProduct(ProductDto dto, MultipartFile[] files, @AuthenticationPrincipal CurrentUser currentUser) throws IOException;

    void editProduct(ProductDto dto, int id, MultipartFile[] files) throws IOException;

    byte[] getProductImage(String fileName) throws IOException;

    void deleteProduct(int id, User user);

    ProductOverview findById(int id);


    List<ProductOverview> findProductByUser(User user);

    List<ProductOverview> findProductsByRestaurant(int id);
}
