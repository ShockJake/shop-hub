package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.CartService;
import com.university.shophub.backend.services.CategoryService;
import com.university.shophub.backend.services.ProductService;
import com.university.shophub.backend.services.UserService;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/cart")
public record CartController(CartService cartService, UserService userService, ProductService productService,
                             CategoryService categoryService) {
    @GetMapping
    public String cartPage(Model model, Authentication authentication) {
        final User user = userService.getUserByEmail(authentication.getName());

        model.addAttribute("products", cartService.getProducts(user.getId()));
        model.addAttribute("totalPrice", cartService.getTotalPrice(user.getId()));
        model.addAttribute("categories", categoryService.getAllCategories());

        return "cart";
    }



}
