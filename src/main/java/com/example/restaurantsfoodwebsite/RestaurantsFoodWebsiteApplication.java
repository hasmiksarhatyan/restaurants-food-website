package com.example.restaurantsfoodwebsite;

import com.example.restaurantsfoodwebsite.entity.Role;
import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


@SpringBootApplication
@RequiredArgsConstructor
public class RestaurantsFoodWebsiteApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsFoodWebsiteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> byEmail = userRepository.findByEmail("admin@mail.com");
        if (!byEmail.isPresent()) {
            userRepository.save(User.builder()
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.MANAGER)
                    .build());
        }
    }
}
