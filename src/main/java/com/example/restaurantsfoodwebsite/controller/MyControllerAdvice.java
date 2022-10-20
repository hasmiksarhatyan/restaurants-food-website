package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
@RequiredArgsConstructor
public class MyControllerAdvice {

    private final UserDetailsService userDetailsService;

    @ModelAttribute(name = "currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            currentUser = (CurrentUser) userDetailsService.loadUserByUsername(currentUser.getUsername());
            return currentUser.getUser();
        }
        return null;
    }
}
