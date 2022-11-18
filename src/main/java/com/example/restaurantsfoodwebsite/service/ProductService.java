package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.product.CreateProductDto;
import com.example.restaurantsfoodwebsite.dto.product.EditProductDto;
import com.example.restaurantsfoodwebsite.dto.product.ProductOverview;
import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Page<ProductOverview> sortProduct(Pageable pageable, String sortProduct, Integer id);

    List<ProductOverview> findAll();

    void addProduct(CreateProductDto dto, MultipartFile[] files, CurrentUser currentUser) throws IOException;

    void editProduct(EditProductDto dto, int id, MultipartFile[] files) throws IOException;

    byte[] getProductImage(String fileName) throws IOException;

    void deleteProduct(int id, User user);

    ProductOverview findById(int id);


    List<ProductOverview> findProductByUser(User user);

    List<ProductOverview> findProductsByRestaurant(int id);
}
