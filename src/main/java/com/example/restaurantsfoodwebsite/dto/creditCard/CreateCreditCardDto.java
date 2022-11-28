package com.example.restaurantsfoodwebsite.dto.creditCard;

import com.example.restaurantsfoodwebsite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditCardDto {

    private String cardNumber;
    private String cardHolder;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate cardExpiresAt;
    private String cvv;
}
