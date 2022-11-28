package com.example.restaurantsfoodwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    private String additionalAddress;

    private String additionalPhone;

    @CreationTimestamp
    private LocalDateTime orderAt;

    private double totalPrice;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @OneToOne
    private Payment payment;

    @OneToMany
    private List<Product> products;

    @ManyToOne
    private User user;

    private boolean isPaid;
}
