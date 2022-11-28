package com.example.restaurantsfoodwebsite.service;

import com.example.restaurantsfoodwebsite.dto.payment.CreatePaymentDto;
import com.example.restaurantsfoodwebsite.dto.payment.PaymentOverview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PaymentService {

    Page<PaymentOverview> getPayments(Pageable pageable);

    void addPayment(CreatePaymentDto dto);

    void delete(int id);
}


