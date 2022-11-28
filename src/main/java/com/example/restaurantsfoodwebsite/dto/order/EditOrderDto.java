package com.example.restaurantsfoodwebsite.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditOrderDto {

    private String additionalAddress;
    private String additionalPhone;
    private String status;
}
