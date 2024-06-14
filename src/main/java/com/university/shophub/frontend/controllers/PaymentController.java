package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.*;
import com.university.shophub.frontend.payloads.DeliveryAddressPayload;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/payment")
public record PaymentController(WalletService walletService, CartService cartService, UserService userService,
                                CategoryService categoryService, PurchaseService purchaseService, ShippingDetailService shippingDetailService) {

    @GetMapping
    public String paymentPage(Model model, Authentication auth) {
        final User user = userService.getUserByEmail(auth.getName());
        List<Product> products = cartService.getProducts(user.getId());

        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("cartItems", products);
        model.addAttribute("numberOfItems", products.size());
        model.addAttribute("totalPrice", cartService.getTotalPrice(user.getId()));
        model.addAttribute("wallet", walletService.findByUserId(user.getId()));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("deliveryAddress", new DeliveryAddressPayload());
        return "payment";
    }

    @PostMapping(path = "/pay")
    public String payForProducts(@Valid DeliveryAddressPayload shippingDetails, Authentication auth) {
        final User user = userService.getUserByEmail(auth.getName());
        List<Product> productsToBuy = cartService.getProducts(user.getId());
        walletService.manageTransaction(productsToBuy, user.getId());
        productsToBuy.forEach(product -> {
            cartService.deleteProductFromCart(user.getId(), product.getId());
        });

        purchaseService.savePurchases(productsToBuy, user);

        shippingDetailService.saveDetails(shippingDetails, productsToBuy, user.getId());
        return "redirect:/home";
    }
}

