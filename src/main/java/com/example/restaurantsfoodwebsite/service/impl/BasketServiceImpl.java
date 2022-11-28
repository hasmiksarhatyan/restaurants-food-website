package com.example.restaurantsfoodwebsite.service.impl;

import com.example.restaurantsfoodwebsite.dto.basket.BasketOverview;
import com.example.restaurantsfoodwebsite.entity.Basket;
import com.example.restaurantsfoodwebsite.entity.Product;
import com.example.restaurantsfoodwebsite.mapper.BasketMapper;
import com.example.restaurantsfoodwebsite.repository.BasketRepository;
import com.example.restaurantsfoodwebsite.repository.ProductRepository;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import com.example.restaurantsfoodwebsite.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final ProductRepository productRepository;

    @Override
    public Page<BasketOverview> getBaskets(Pageable pageable) {
        Page<Basket> all = basketRepository.findAll(pageable);
        List<BasketOverview> basketOverviews = basketMapper.mapToDto(all);
        return new PageImpl<>(basketOverviews, pageable, basketOverviews.size());
    }

    @Override
    public void addBasket(int id, CurrentUser currentUser) {
        Basket basket = new Basket();
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new IllegalStateException();
        }
        Product product = productOptional.get();
        if (!basketRepository.existsByProduct(product)) {
            basket.setProduct(product);
            basket.setQuantity(1);
            basket.setUser(currentUser.getUser());
            basketRepository.save(basket);
        } else {
            basket = basketRepository.findByProductAndUser(product, currentUser.getUser());
            basket.setQuantity(basket.getQuantity()+1);
            basketRepository.save(basket);
        }
    }

    @Override
    public void delete(int id) {
        if (!basketRepository.existsById(id)) {
            throw new IllegalStateException();
        }
        basketRepository.deleteById(id);
    }
}
