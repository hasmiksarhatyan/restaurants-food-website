package com.example.restaurantsfoodwebsite.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditEventDto {

    private String name;
    private String description;
    private double price;
    private String eventDateTime;
    private Integer restaurantId;
    private List<String> pictures;
}
