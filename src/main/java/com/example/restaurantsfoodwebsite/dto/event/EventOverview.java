package com.example.restaurantsfoodwebsite.dto.event;

import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventOverview {
    private int id;
    private String name;
    private String description;
    private double price;
    private LocalDateTime eventDateTime;
    private RestaurantOverview restaurantOverview;
    private List<String> pictures;
}
