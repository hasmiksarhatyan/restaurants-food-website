package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.user.ChangePasswordDto;
import com.example.restaurantsfoodwebsite.dto.user.CreateUserDto;
import com.example.restaurantsfoodwebsite.dto.user.EditUserDto;
import com.example.restaurantsfoodwebsite.dto.user.UserOverview;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import com.example.restaurantsfoodwebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static String ERROR;


    @GetMapping
    public String users(@RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size,
                        ModelMap modelMap) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<UserOverview> users = userService.getUsers(PageRequest.of(currentPage - 1, pageSize));

        modelMap.addAttribute("users", users);

        int totalPages = users.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "users";
    }

    @GetMapping("/add")
    public String addUserPage() {
        return "addUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute CreateUserDto dto, ModelMap modelMap, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/addUser";
        }
        try {
            userService.saveUser(dto);
            return "redirect:/loginPage";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageEmail", "Email already in use");
            return "/addUser";
        }
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("token") String token) throws Exception {
        userService.verifyUser(token);
        return "index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, ModelMap modelMap) {
        try {
            userService.delete(id);
            return "redirect:/users";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageId", "Something went wrong, Try again!");
            return "users";
        }
    }

    @GetMapping("/customer")
    public String customerPage(@AuthenticationPrincipal CurrentUser currentUser,
                               ModelMap modelMap) {
        modelMap.addAttribute("user", currentUser.getUser());
        String error = ERROR;
        modelMap.addAttribute("errorMessage", error);
        ERROR = null;
        return "customer";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute EditUserDto dto,
                           @AuthenticationPrincipal CurrentUser currentUser) {
        try {
            userService.editUser(dto, currentUser.getUser());
            return "redirect:/users/customer";
        } catch (IllegalStateException ex) {
            ERROR = ex.getMessage();
            return "redirect:/users/customer";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute ChangePasswordDto dto,
                                 @AuthenticationPrincipal CurrentUser currentUser) {
        try {
            userService.changePassword(currentUser.getUser().getId(), dto);
            return "redirect:/users/customer";
        } catch (IllegalStateException ex) {
            ERROR = ex.getMessage();
            return "redirect:/users/customer";
        }
    }
}
