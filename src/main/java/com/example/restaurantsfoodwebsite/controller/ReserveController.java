package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.reserve.EditReserveDto;
import com.example.restaurantsfoodwebsite.dto.reserve.ReserveOverview;
import com.example.restaurantsfoodwebsite.entity.Role;
import com.example.restaurantsfoodwebsite.security.CurrentUser;
import com.example.restaurantsfoodwebsite.service.ReserveService;
import com.example.restaurantsfoodwebsite.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReserveController {

    private final ReserveService reserveService;

    @GetMapping
    public String reservations(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "6") int size,
                               ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {

        if (currentUser.getUser().getRole() == Role.MANAGER) {
            Page<ReserveOverview> reserveOverviews = reserveService.getAll(PageRequest.of(page - 1, size));
            modelMap.addAttribute("reservations", reserveOverviews);
            modelMap.addAttribute("pageNumbers", PageUtil.getTotalPages(reserveOverviews));
        }
        if (currentUser.getUser().getRole() == Role.RESTAURANT_OWNER) {
            Page<ReserveOverview> reserveByRestaurant = reserveService.getReserveByRestaurant(currentUser.getUser(),PageRequest.of(page-1,size));
            modelMap.addAttribute("reservations", reserveByRestaurant);
            modelMap.addAttribute("pageNumbers", PageUtil.getTotalPages(reserveByRestaurant));
        }
        if (currentUser.getUser().getRole() == Role.CUSTOMER) {
            Page<ReserveOverview> reserveByUser = reserveService.getReserveByUser(currentUser.getUser().getId(), PageRequest.of(page - 1, size));
            modelMap.addAttribute("reservations", reserveByUser);
            modelMap.addAttribute("pageNumbers", PageUtil.getTotalPages(reserveByUser));
        }
        return "reservations";
    }

    @PostMapping("/edit/{id}")
    public String editReserve(@PathVariable("id") int id, @ModelAttribute EditReserveDto dto, ModelMap modelMap) {
        modelMap.addAttribute("reserve", reserveService.getById(id));
        reserveService.editReserve(dto, id);
        return "redirect:/reservations";
    }


    @GetMapping("/delete/{id}")
    public String deleteReserve(@PathVariable("id") int id) {

        reserveService.delete(id);
        return "redirect:/reservations";

    }

}
