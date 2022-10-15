package com.example.restaurantsfoodwebsite.dto.user;

import com.example.restaurantsfoodwebsite.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOverview {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
