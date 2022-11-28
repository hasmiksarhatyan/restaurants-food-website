package com.example.restaurantsfoodwebsite.dto.creditCard;

import com.example.restaurantsfoodwebsite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardOverview {

    private Integer id;
    private int cardNumber;
    private String cardHolder;
    private LocalDate cardExpiresAt;
    private int cvv;
    private User user;
}
