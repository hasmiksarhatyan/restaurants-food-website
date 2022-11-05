package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.product.ProductOverview;
import com.example.restaurantsfoodwebsite.dto.productCategory.ProductCategoryOverview;
import com.example.restaurantsfoodwebsite.dto.restaurant.CreateRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.EditRestaurantDto;
import com.example.restaurantsfoodwebsite.dto.restaurant.RestaurantOverview;
import com.example.restaurantsfoodwebsite.entity.Restaurant;
import com.example.restaurantsfoodwebsite.entity.Role;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import com.example.restaurantsfoodwebsite.service.ProductCategoryService;
import com.example.restaurantsfoodwebsite.service.ProductService;
import com.example.restaurantsfoodwebsite.service.RestaurantCategoryService;
import com.example.restaurantsfoodwebsite.service.RestaurantService;
import com.example.restaurantsfoodwebsite.util.PageUtil;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantCategoryService restaurantCategoryService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private static String ERROR;


    @GetMapping
    public String restaurants(@RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "5") int size,
                              ModelMap modelMap) {
        Page<RestaurantOverview> restaurants = restaurantService.findAllRestaurants(PageRequest.of(page, size));
        modelMap.addAttribute("restaurants", restaurants);
        modelMap.addAttribute("pageNumbers", PageUtil.getTotalPages(restaurants));
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
            if (currentUser.getUser().getRole() == Role.RESTAURANT_OWNER) {
                return "redirect:/restaurants/my";
            } else {
                return "redirect:/restaurants";
            }
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
    public String deleteRestaurant(@PathVariable("id") int id,
                                   @AuthenticationPrincipal CurrentUser currentUser,
                                   ModelMap modelMap) {
        try {
            restaurantService.deleteRestaurant(id);
            if (currentUser.getUser().getRole().equals(Role.RESTAURANT_OWNER)) {
                return "redirect:/restaurants/my";
            }
            return "redirect:/restaurants";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageId", "Something went wrong, Try again!");
            return "restaurants";
        }
    }

    @GetMapping("/my")
    public String restaurantsForOwner(@RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @AuthenticationPrincipal CurrentUser currentUser,
                                      ModelMap modelMap) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        try {
            Page<RestaurantOverview> restaurants = restaurantService.getRestaurantsByUser(currentUser.getUser(), PageRequest.of(currentPage - 1, pageSize));
            modelMap.addAttribute("restaurants", restaurants);
            List<Integer> pageNumbers = PageUtil.getTotalPages(restaurants);
            modelMap.addAttribute("pageNumbers", pageNumbers);
            return "restaurants";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageEmptyRestaurant", e.getMessage());
            return "addRestaurant";
        }
    }

    @GetMapping("/edit/{id}")
    public String editRestaurant(@PathVariable("id") int id,
                                 @AuthenticationPrincipal CurrentUser currentUser,
                                 ModelMap modelMap) {
        try {
            modelMap.addAttribute("restaurantId", id);
            modelMap.addAttribute("categories", restaurantCategoryService.findAll());
            return "editMyRestaurants";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageEdit", "Please, try again");
            if (currentUser.getUser().getRole() == Role.RESTAURANT_OWNER) {
                return "restaurantForVisitor";
            } else {
                return "restaurants";
            }
        }
    }

    @PostMapping("/edit/{id}")
    public String editRestaurant(@PathVariable("id") int id,
                                 @ModelAttribute EditRestaurantDto dto,
                                 ModelMap modelMap) {
        try {
            restaurantService.editRestaurant(dto, id);
            return "redirect:/restaurants";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageEdit", e.getMessage());
            return "editMyRestaurants";
        }
    }

    @GetMapping("/{id}")
    public String singleRestaurantPage(@PathVariable int id,
                                       @AuthenticationPrincipal CurrentUser currentUser,
                                       ModelMap modelMap) {
        try {
            Restaurant restaurant = restaurantService.findRestaurant(id);
            modelMap.addAttribute("restaurant", restaurant);
            List<ProductOverview> products = productService.findProductsByRestaurant(id);
            modelMap.addAttribute("products",products);
            List<ProductCategoryOverview> categories = productCategoryService.findAll();
            modelMap.addAttribute("categories",categories);
            if (currentUser == null) {
                return "restaurantForVisitor";
            }
            return "indicatedRestaurant";
        } catch (IllegalStateException ex) {
            modelMap.addAttribute("errorMessageSingle", ex.getMessage());
            return "redirect:/restaurants/my";
        }
    }
}

