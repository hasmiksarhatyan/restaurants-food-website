package com.example.restaurantsfoodwebsite.dto.reserve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReserveDto {

    private String reservedDate;
    private String reservedTime;
    private int peopleCount;
    private String phoneNumber;
    private Integer restaurantId;

}
