package com.example.restaurantsfoodwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservedAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservedFor;
    @ManyToOne
    private Restaurant restaurant;
    @ManyToOne
    private User user;
    private int hostCount;
}
