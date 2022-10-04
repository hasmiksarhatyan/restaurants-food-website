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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private PaymentOption paymentOption;
    private Double paymentAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paidAt;
    @ManyToOne
    private User user;
    @ManyToOne
    private Order order;
}
