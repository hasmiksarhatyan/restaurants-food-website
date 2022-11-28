package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.order.CreateOrderDto;
import com.example.restaurantsfoodwebsite.dto.order.OrderOverview;
import com.example.restaurantsfoodwebsite.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.restaurantsfoodwebsite.util.PageUtil.getTotalPages;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String orders(@RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "5") int size,
                         ModelMap modelMap) {
        Page<OrderOverview> orders = orderService.getOrders(PageRequest.of(page, size));
        modelMap.addAttribute("orders", orders);
        List<Integer> pageNumbers = getTotalPages(orders);
        modelMap.addAttribute("pageNumbers", pageNumbers);
        return "orders";
    }

    @GetMapping("/add")
    public String addOrderPage() {
        return "addOrder";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute CreateOrderDto dto) {
        orderService.addOrder(dto);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            orderService.delete(id);
            return "redirect:/orders";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageId", "Something went wrong, Try again!");
            return "orders";
        }
    }
}
