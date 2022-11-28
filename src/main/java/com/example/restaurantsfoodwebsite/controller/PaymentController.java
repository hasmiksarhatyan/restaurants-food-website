package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.payment.CreatePaymentDto;
import com.example.restaurantsfoodwebsite.dto.payment.PaymentOverview;
import com.example.restaurantsfoodwebsite.service.PaymentService;
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
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public String payments(@RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size,
                           ModelMap modelMap) {
        Page<PaymentOverview> payments = paymentService.getPayments(PageRequest.of(page, size));
        modelMap.addAttribute("payments", payments);
        List<Integer> pageNumbers = getTotalPages(payments);
        modelMap.addAttribute("pageNumbers", pageNumbers);
        return "payments";
    }

    @GetMapping("/add")
    public String addPaymentPage() {
        return "addPayment";
    }

    @PostMapping("/add")
    public String addPayment(@ModelAttribute CreatePaymentDto createPaymentDto) {
        paymentService.addPayment(createPaymentDto);
        return "redirect:/payments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, ModelMap modelMap) {
        try {
            paymentService.delete(id);
            return "redirect:/payments";
        } catch (IllegalStateException e) {
            modelMap.addAttribute("errorMessageId", "Something went wrong, Try again!");
            return "payments";
        }
    }

}
