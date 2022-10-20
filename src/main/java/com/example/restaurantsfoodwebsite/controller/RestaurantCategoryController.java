package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.restaurantCategory.CreateRestaurantCategoryDto;
import com.example.restaurantsfoodwebsite.dto.restaurantCategory.RestaurantCategoryOverview;
import com.example.restaurantsfoodwebsite.service.RestaurantCategoryService;
import com.example.restaurantsfoodwebsite.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurantsCategory")
public class RestaurantCategoryController {

    private final RestaurantCategoryService restaurantCategoryService;

    @GetMapping
    public String restaurants(@RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size",defaultValue = "5") int size,
                              ModelMap modelMap) {
        Page<RestaurantCategoryOverview> categories = restaurantCategoryService.findAll(PageRequest.of(page, size));
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("pageNumbers", PageUtil.getTotalPages(categories));
        return "restaurantCategory";
    }

    @GetMapping("/add")
    public String addRestaurantPage() {
        return "addRestaurantCategory";
    }

    @PostMapping("/add")
    public String addRestaurant(@ModelAttribute CreateRestaurantCategoryDto dto) {
        restaurantCategoryService.addRestaurantCategory(dto);
        return "redirect:/restaurantsCategory";
    }

    @GetMapping("/delete/{id}")
    public String deleteRestaurantCategory(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            restaurantCategoryService.deleteRestaurantCategory(id);
            return "redirect:/restaurantsCategory";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageId", "Something went wrong, Try again!");
            return "restaurantCategory";
        }
    }
}
