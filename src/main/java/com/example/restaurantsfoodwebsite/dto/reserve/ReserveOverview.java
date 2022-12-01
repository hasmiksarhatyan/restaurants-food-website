package com.example.restaurantsfoodwebsite.dto.reserve;

import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import com.example.restaurantsfoodwebsite.dto.user.UserOverview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveOverview {

    private Integer id;
    private LocalDateTime reservedAt;
    private LocalDate reservedDate;
    private LocalTime reservedTime;
    private int peopleCount;
    private String phoneNumber;
    private String status;
    private RestaurantOverview restaurantOverview;
    private UserOverview userOverview;
}
