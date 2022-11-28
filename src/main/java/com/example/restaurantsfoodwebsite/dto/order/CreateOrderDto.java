package com.example.restaurantsfoodwebsite.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

    private String additionalPhone;
    private String additionalAddress;
//    private List<Integer> products;
}
