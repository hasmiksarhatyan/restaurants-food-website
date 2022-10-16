package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.restaurantCategory.CreateRestaurantCategoryDto;
import com.example.restaurantsfoodwebsite.dto.restaurantCategory.RestaurantCategoryOverview;
import com.example.restaurantsfoodwebsite.service.RestaurantCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurantsCategory")
public class RestaurantCategoryController {

    private final RestaurantCategoryService restaurantCategoryService;

    @GetMapping
    public String restaurants(@RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size,
                              ModelMap modelMap) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<RestaurantCategoryOverview> categories = restaurantCategoryService.findAll(PageRequest.of(currentPage, pageSize));
        modelMap.addAttribute("categories", categories);

        int totalPages = categories.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
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
