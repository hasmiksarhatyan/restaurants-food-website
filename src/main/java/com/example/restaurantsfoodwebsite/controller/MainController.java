package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.entity.Role;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap modelMap) {
        if (error != null && error.equals("true")) {
            modelMap.addAttribute("error", true);
        }
        return "loginPage";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getRole() == Role.CUSTOMER || user.getRole() == Role.RESTAURANT_OWNER) {
                return "redirect:/users/customer";
            } else if (user.getRole() == Role.MANAGER) {
                return "manager";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}