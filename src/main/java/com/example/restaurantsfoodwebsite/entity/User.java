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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateAt;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
}
