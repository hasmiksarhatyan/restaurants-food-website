package com.example.restaurantsfoodwebsite.dto.payment;

import com.example.restaurantsfoodwebsite.entity.PaymentOption;
import com.example.restaurantsfoodwebsite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOverview {

    private int id;
    private PaymentOption paymentOption;
    private Double paymentAmount;
    private LocalDateTime paidAt;
    private User user;
}
