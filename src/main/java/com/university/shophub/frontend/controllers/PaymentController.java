package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.CartService;
import com.university.shophub.backend.services.CategoryService;
import com.university.shophub.backend.services.UserService;
import com.university.shophub.backend.services.WalletService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public record PaymentController(WalletService walletService, CartService cartService, UserService userService,
                                CategoryService categoryService) {

    @GetMapping
    public String paymentPage(Model model, Authentication auth) {
        final User user = userService.getUserByEmail(auth.getName());

        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("totalPrice", cartService.getTotalPrice(user.getId()));
        model.addAttribute("cartItems", cartService.getProducts(user.getId()));
        model.addAttribute("wallet", walletService.findByUserId(user.getId()));
        model.addAttribute("categories", categoryService.getAllCategories());

        return "payment";
    }
}

