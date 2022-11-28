package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.creditCard.CreateCreditCardDto;
import com.example.restaurantsfoodwebsite.dto.creditCard.CreditCardOverview;
import com.example.restaurantsfoodwebsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CreditCardService {

    Page<CreditCardOverview> getCreditCards(Pageable pageable, User user);

    void addCreditCard(CreateCreditCardDto createCreditCardDto, User user);
}


