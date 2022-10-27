package com.example.restaurantsfoodwebsite.dto.event;

import com.example.restaurantsfoodwebsite.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private String name;
    private String description;
    private double price;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date eventDateTime;
    private Restaurant restaurant;
    private List<String> pictures;
}
