package com.example.restaurantsfoodwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private double price;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date eventDateTime;

    @ManyToOne
    private Restaurant restaurant;

    @ElementCollection
    private List<String> pictures;


}
