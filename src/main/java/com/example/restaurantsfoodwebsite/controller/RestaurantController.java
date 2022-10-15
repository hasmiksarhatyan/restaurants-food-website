package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.restaurant.CreateRestaurantDto;
import com.example.restaurantsfoodwebsite.service.RestaurantCategoryService;
import com.example.restaurantsfoodwebsite.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantCategoryService restaurantCategoryService;

    @GetMapping
    public String restaurants(ModelMap modelMap) {
        modelMap.addAttribute("restaurants", restaurantService.findAllRestaurants());
        return "restaurants";
    }

    @GetMapping("/add")
    public String addRestaurantPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", restaurantCategoryService.findAll());
        return "addRestaurant";
    }

    @PostMapping("/add")
    public String addRestaurant(@RequestParam("restaurantImage") MultipartFile[] files,
                                @ModelAttribute CreateRestaurantDto dto,
                                ModelMap modelMap) throws IOException {

        for (MultipartFile file : files) {
            if (!file.isEmpty() && file.getSize() > 0) {
                if (file.getContentType() != null && !file.getContentType().contains("image")) {
                    modelMap.addAttribute("errorMessageFile", "Please choose only image");
                    return "addRestaurant";
                }
            }
        }
        try {
            restaurantService.addRestaurant(dto, files);
            return "redirect:/restaurants";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageEmail", "Email already in use");
            return "redirect:/restaurants/add";
        }
    }

    @GetMapping(value = "/getImages", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return restaurantService.getRestaurantImage(fileName);
    }

    @GetMapping("/delete")
    public String deleteRestaurant(@RequestParam("id") int id, ModelMap modelMap) {
        try {
            restaurantService.deleteRestaurant(id);
            return "redirect:/restaurants";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageId", "Something went wrong, Try again!");
            return "restaurants";
        }
    }
}
