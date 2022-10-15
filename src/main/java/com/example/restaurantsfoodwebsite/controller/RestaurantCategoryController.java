package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.restaurantCategory.CreateRestaurantCategoryDto;
import com.example.restaurantsfoodwebsite.service.RestaurantCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurantsCategory")
public class RestaurantCategoryController {

    private final RestaurantCategoryService restaurantCategoryService;

    @GetMapping
    public String restaurants(ModelMap modelMap) {
        modelMap.addAttribute("restaurantsCategory", restaurantCategoryService.findAll());
        return "restaurantsCategory";
    }

    @GetMapping("/add")
    public String addRestaurantPage() {
        return "addRestaurantCategory";
    }

    @PostMapping("/add")
    public String addRestaurant(@ModelAttribute CreateRestaurantCategoryDto dto) throws IOException {
        restaurantCategoryService.addRestaurantCategory(dto);
        return "redirect:/restaurantsCategory";
    }

    @GetMapping("/delete")
    public String deleteRestaurantCategory(@RequestParam("id") int id, ModelMap modelMap) {
        try {
            restaurantCategoryService.deleteRestaurantCategory(id);
            return "redirect:/restaurantsCategory";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageId", "Something went wrong, Try again!");
            return "restaurantsCategory";
        }
    }
}
