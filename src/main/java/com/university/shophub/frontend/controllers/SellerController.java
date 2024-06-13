package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.CategoryService;
import com.university.shophub.backend.services.ProductService;
import com.university.shophub.backend.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller")
public record SellerController(UserService userService, ProductService productService,
                               CategoryService categoryService) {

    @GetMapping("/{id}")
    public String getSellerDashboard(@PathVariable String id, Model model) {
        User seller = userService.getUserById(id);
        model.addAttribute("sellerId", seller.getId());
        model.addAttribute("sellerName", seller.getName());
        model.addAttribute("sellerEmail", seller.getEmail());
        model.addAttribute("sellerRole", seller.getRole());
        model.addAttribute("sellerCreatedAt", seller.getCreatedAt());
        model.addAttribute("sellerProducts", productService.getProductsBySellerName(seller.getEmail()));
        model.addAttribute("categories", categoryService.getAllCategories());

        return "seller-dashboard";
    }
}