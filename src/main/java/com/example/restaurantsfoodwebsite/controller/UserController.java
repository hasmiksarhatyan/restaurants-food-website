package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.entity.User;
import com.example.restaurantsfoodwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/users")
    public String users(ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserPage() {
        return "addUser";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user, ModelMap modelMap) throws IOException {
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            modelMap.addAttribute("errorMessageEmail", "Email already in use");
            return "/addUser";
        }
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        userRepository.save(user);
        return "redirect:/loginPage";
    }

    @GetMapping("/users/delete")
    public String delete(@RequestParam("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
