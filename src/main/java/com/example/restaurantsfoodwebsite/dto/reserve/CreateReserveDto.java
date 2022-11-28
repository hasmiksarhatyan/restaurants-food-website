package com.example.restaurantsfoodwebsite.dto.reserve;

import com.example.restaurantsfoodwebsite.entity.Restaurant;
import com.example.restaurantsfoodwebsite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReserveDto {

    private LocalDateTime reservedAt;
    private LocalDate reservedFor;
    private Restaurant restaurant;
    private User user;
    private int hostCount;
}
