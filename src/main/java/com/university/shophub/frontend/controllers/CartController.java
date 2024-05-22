package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.CartService;
import com.university.shophub.backend.services.ProductService;
import com.university.shophub.backend.services.UserService;
import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public record CartController(CartService cartService, UserService userService, ProductService productService)
{
    @GetMapping
    public String cartPage(Model model, Authentication authentication)
    {
        final User user = userService.getUserByEmail(authentication.getName());

        model.addAttribute("products", cartService.getProducts(user.getId()));

        return "cart";
    }

    @PostMapping("/{id}")
    public String addProduct(@PathVariable @NonNull String id, Model model, Authentication authentication)
    {
        final User user = userService.getUserByEmail(authentication.getName());

        cartService.addProductToCart(user.getId(), Long.parseLong(id));
        model.addAttribute("products", cartService.getProducts(user.getId()));

        return "cart";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable @NonNull String id, Model model, Authentication authentication)
    {
        final User user = userService.getUserByEmail(authentication.getName());

        cartService.deleteProductFromCart(user.getId(), Long.parseLong(id));
        model.addAttribute("products", cartService.getProducts(user.getId()));

        return "cart";
    }

}
