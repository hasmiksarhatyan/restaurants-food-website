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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderAt;
    @Enumerated(value = EnumType.STRING)
    private StatusRole orderStatus;
    private String additionalAddress;
    private String phoneNumber;
    @ManyToOne
    private User user;
}
