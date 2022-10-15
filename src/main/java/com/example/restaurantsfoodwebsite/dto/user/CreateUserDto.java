package com.example.restaurantsfoodwebsite.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    //    @Size(min = 2, max = 30)
    private String firstName;

    //    @Size(min = 2, max = 30)
    private String lastName;

    //    @NotBlank(message = "Enter your email")
//    @Email(message = "Enter a valid email address")
    private String email;

    //    @NotBlank(message = "Enter your password")
//    @Size(min = 1, max = 10, message = "Password must be at least 6 characters")
    private String password;

    private String verifyToken;

    private boolean enabled;
}