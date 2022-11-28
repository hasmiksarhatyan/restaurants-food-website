package com.example.restaurantsfoodwebsite.dto.basket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBasketDto {

//    private double quantity;
    private int productId;
}
