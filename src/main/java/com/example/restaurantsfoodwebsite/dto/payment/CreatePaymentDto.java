package com.example.restaurantsfoodwebsite.dto.payment;

import com.example.restaurantsfoodwebsite.entity.PaymentOption;
import com.example.restaurantsfoodwebsite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDto {

    private PaymentOption paymentOption;
    private Double paymentAmount;
    private User user;
}
