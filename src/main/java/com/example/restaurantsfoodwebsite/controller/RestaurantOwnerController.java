package com.example.restaurantsfoodwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RestaurantOwnerController {

    @GetMapping("/restaurantOwner")
    public String users() {
        return "restaurantOwner";
    }
}
