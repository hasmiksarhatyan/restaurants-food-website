package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.product.ProductDto;
import com.example.restaurantsfoodwebsite.dto.product.ProductOverview;
import com.example.restaurantsfoodwebsite.entity.*;
import com.example.restaurantsfoodwebsite.mapper.ProductMapper;
import com.example.restaurantsfoodwebsite.repository.ProductCategoryRepository;
import com.example.restaurantsfoodwebsite.repository.ProductRepository;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import com.example.restaurantsfoodwebsite.service.ProductService;
import com.example.restaurantsfoodwebsite.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductMapper productMapper;
    private final FileUtil fileUtil;

    @Override
    public Page<ProductOverview> sortProduct(Pageable pageable, String sort, Integer id) {
        Page<Product> products;
        if (id != null) {
            products = productRepository.findProductsByProductCategory_Id(id, pageable);
            return products.map(productMapper::mapToOverview);
        }
        switch (sort) {
            case "price_asc":
                products = productRepository.findByOrderByPriceAsc(pageable);
                break;
            case "price_desc":
                products = productRepository.findByOrderByPriceDesc(pageable);
                break;
            default:
                products = productRepository.findAll(pageable);
        }

        return products.map(productMapper::mapToOverview);

    }

    public List<ProductOverview> findAll() {
        return productMapper.mapToOverviewList(productRepository.findAll());
    }


    @Override
    public void addProduct(ProductDto dto, MultipartFile[] files, @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        if (StringUtils.hasText(dto.getName()) && dto.getPrice() >= 0) {
            dto.setPictures(fileUtil.uploadImages(files));
            productRepository.save(productMapper.mapToEntity(dto, currentUser));
        }
    }

    @Override
    public void editProduct(ProductDto dto, int id, MultipartFile[] files) throws IOException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            String name = dto.getName();
            if (StringUtils.hasText(name)) {
                product.setName(name);
            }
            String description = dto.getDescription();
            if (StringUtils.hasText(name)) {
                product.setDescription(description);
            }
            Double price = dto.getPrice();
            if (price >= 0) {
                product.setPrice(price);
            }
            Restaurant restaurant = dto.getRestaurant();
            if (restaurant != null) {
                product.setRestaurant(restaurant);
            }
            ProductCategory productCategory = dto.getProductCategory();
            if (productCategory != null) {
                product.setProductCategory(productCategory);
            }
            List<String> pictures = dto.getPictures();
            if (pictures != null) {
                product.setPictures(fileUtil.uploadImages(files));
            }
            productRepository.save(product);
        }
    }

    @Override
    public byte[] getProductImage(String fileName) throws IOException {
        return fileUtil.getImage(fileName);
    }

    @Override
    public void deleteProduct(int id, User user) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            if ((user.getRole() == Role.MANAGER) ||
                    (user.getId().equals(productOptional.get().getUser().getId()))) {
                productRepository.deleteById(id);
            }
        }
    }


    @Override
    public ProductOverview findById(int id) {
        return productMapper.mapToOverview(productRepository.getReferenceById(id));
    }

    @Override
    public List<ProductOverview> findProductByUser(User user) {
        return productMapper.mapToOverviewList(productRepository.findProductByUser(user));
    }

    @Override
    public List<ProductOverview> findProductsByRestaurant(int id) {
        return productMapper.mapToOverviewList(productRepository.findProductsByRestaurant_Id(id));
    }

}
