package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.restaurant.CreateRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.EditRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import com.example.restaurantsfoodwebsite.entity.Role;
import com.example.restaurantsfoodwebsite.repository.RestaurantRepository;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import com.example.restaurantsfoodwebsite.service.RestaurantCategoryService;
import com.example.restaurantsfoodwebsite.service.RestaurantService;
import com.example.restaurantsfoodwebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantCategoryService restaurantCategoryService;
    private static String ERROR;

    @GetMapping
    public String restaurants(@RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size,
                              ModelMap modelMap) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<RestaurantOverview> restaurants = restaurantService.findAllRestaurants(PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("restaurants", restaurants);

        int totalPages = restaurants.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "restaurants";
    }

    @GetMapping("/add")
    public String addRestaurantPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", restaurantCategoryService.findAll());
        String errorMessageEmail = ERROR;
        modelMap.addAttribute("errorMessageEmail", errorMessageEmail);
        return "addRestaurant";
    }

    @PostMapping("/add")
    public String addRestaurant(@RequestParam("restaurantImage") MultipartFile[] files,
                                @ModelAttribute CreateRestaurantDto dto,
                                @AuthenticationPrincipal CurrentUser currentUser,
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
            restaurantService.addRestaurant(dto, files, currentUser);
            if (currentUser.getUser().getRole().equals(Role.RESTAURANT_OWNER)) {
                return "redirect:/restaurants/myRestaurants";
            }
            return "redirect:/restaurants";
        } catch (IllegalStateException e) {
            ERROR = e.getMessage();
            return "redirect:/restaurants/add";
        }
    }

    @GetMapping(value = "/getImages", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return restaurantService.getRestaurantImage(fileName);
    }

    @GetMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            restaurantService.deleteRestaurant(id);
            return "redirect:/restaurants";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageId", "Something went wrong, Try again!");
            return "restaurants";
        }
    }

    @GetMapping("/myRestaurants")
    public String restaurantsForOwner(@RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @AuthenticationPrincipal CurrentUser currentUser,
                                      ModelMap modelMap) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        try {
            Page<RestaurantOverview> restaurants = restaurantService.getRestaurantsByUser(currentUser.getUser(), PageRequest.of(currentPage - 1, pageSize));
            modelMap.addAttribute("restaurants", restaurants);

            int totalPages = restaurants.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                modelMap.addAttribute("pageNumbers", pageNumbers);
            }
            return "myRestaurants";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageEmptyRestaurant", e.getMessage());
            return "addRestaurant";
        }
    }

    @PostMapping("/editRestaurant/{id}")
    public String editRestaurant(@PathVariable int id,
                                 @ModelAttribute EditRestaurantDto dto,
                                 ModelMap modelMap) {
        try {
            restaurantService.editRestaurant(dto, id);
            return "redirect:/restaurants/myRestaurants";
        } catch (IllegalStateException ex) {
            modelMap.addAttribute("errorMessageEdit", ex.getMessage());
            return "myRestaurants";
        }
    }


    @GetMapping("/edit/{id}")
    public String editRestaurant(@PathVariable("id") int id,
                                 ModelMap modelMap) {
        try {
            modelMap.addAttribute("restaurantId", id);
            modelMap.addAttribute("categories", restaurantCategoryService.findAll());
            return "editMyRestaurants";
        } catch (IllegalStateException ex) {
            modelMap.addAttribute("errorMessageEdit", ex.getMessage());
            return "myRestaurants";
        }
    }
}

