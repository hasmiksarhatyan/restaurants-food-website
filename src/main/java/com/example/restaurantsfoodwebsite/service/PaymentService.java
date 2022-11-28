package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.creditCard.CreateCreditCardDto;
import com.example.restaurantsfoodwebsite.dto.payment.CreatePaymentDto;
import com.example.restaurantsfoodwebsite.dto.payment.PaymentOverview;
import com.example.restaurantsfoodwebsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PaymentService {

    Page<PaymentOverview> getPayments(Pageable pageable);

    boolean addPayment(CreatePaymentDto dto, CreateCreditCardDto creditCardDto, User user);

    void delete(int id);
}


