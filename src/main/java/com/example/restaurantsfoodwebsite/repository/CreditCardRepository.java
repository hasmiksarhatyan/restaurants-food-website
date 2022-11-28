package com.example.restaurantsfoodwebsite.repository;

import com.example.restaurantsfoodwebsite.entity.Basket;
import com.example.restaurantsfoodwebsite.entity.CreditCard;
import com.example.restaurantsfoodwebsite.entity.Product;
import com.example.restaurantsfoodwebsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

//    boolean existsByProduct(Product product);
//
//    boolean existsByProductId(int id);
//
//    Basket findBasketByProductId(int id);
//
//    List<Product> findAllByProductId(int id);
//
//    Page<Basket> findBasketByUser(User user, Pageable pageable);

    Page<CreditCard> findCreditCardByUser(User use, Pageable pageable);


//    Optional<Basket> findByUser(User user);
}
