package com.example.restaurantsfoodwebsite.dto.payment;

import com.example.restaurantsfoodwebsite.entity.PaymentOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDto {

    private double totalPrice;
    private PaymentOption paymentOption;
}
