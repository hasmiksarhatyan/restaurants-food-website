package com.example.restaurantsfoodwebsite.controller;

import com.example.restaurantsfoodwebsite.dto.productCategory.CreateProductCategoryDto;
import com.example.restaurantsfoodwebsite.dto.productCategory.EditProductCategoryDto;
import com.example.restaurantsfoodwebsite.dto.productCategory.ProductCategoryOverview;
import com.example.restaurantsfoodwebsite.service.ProductCategoryService;
import com.example.restaurantsfoodwebsite.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/productsCategories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;


    @GetMapping
    public String productCategories(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "5") int size,
                                    ModelMap modelMap) {
        Page<ProductCategoryOverview> categories = productCategoryService.findAll(PageRequest.of(page - 1, size));
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("pageNumbers", PageUtil.getTotalPages(categories));
        return "/manager/productCategories";

    }


    @GetMapping("/add")
    public String addProductCategoryPage() {
        return "/manager/addProductCategory";
    }

    @PostMapping("/add")
    public String addProductCategory(@ModelAttribute CreateProductCategoryDto dto) {
        productCategoryService.addProductCategory(dto);
        return "redirect:/productsCategories";
    }


    @GetMapping("/edit/{id}")
    public String editProductCategoryPage(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("category", productCategoryService.findById(id));
        return "/manager/editProductCategory";
    }

    @PostMapping("/edit/{id}")
    public String editProductCategory(@PathVariable("id") int id, @ModelAttribute EditProductCategoryDto dto) {
        productCategoryService.editProductCategory(dto, id);
        return "redirect:/productsCategories";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductCategory(@PathVariable("id") int id) {

        productCategoryService.deleteProductCategory(id);
        return "redirect:/productsCategories";

    }
}