package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.models.*;
import com.university.shophub.backend.services.*;
import com.university.shophub.frontend.payloads.ChangePasswordPayload;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/account")
public record AccountController(UserService userService, CategoryService categoryService, RequestService requestService,
                                WalletService walletService, ProductService productService,
                                PurchaseService purchaseService) {
    @GetMapping
    public String account(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/p4/login";
        }
        final User user = userService.getUserByEmail(authentication.getName());

        try {
            Request request = requestService.getRequestByRequesterId(user.getId());
            if (request.getRequestStatus().equals(RequestStatus.PENDING)) {
                model.addAttribute("requested", request.getRequestType());
            }
        } catch (Exception e) {/*ignored*/}

        Wallet wallet = walletService.findByUserId(user.getId());
        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userRole", user.getRole().name());
        model.addAttribute("userCreatedAt", user.getCreatedAt().getYear());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getProductsBySellerName(user.getName()));
        model.addAttribute("walletBalance", wallet.getBalance());
        model.addAttribute("transactions", wallet.getHistory());

        model.addAttribute("walletId", wallet.getUserId());
        model.addAttribute("purchases", purchaseService.getLastPurchasesByUserId(user.getId()));
        return "account";
    }

    @GetMapping("/purchases")
    public String purchases(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/p4/login";
        }
        final User user = userService.getUserByEmail(authentication.getName());
        user.setPassword("hidden");
        model.addAttribute("purchases", purchaseService.getPurchasesByUserId(user.getId()));
        model.addAttribute("user", user);
        return "purchases";
    }

    @ModelAttribute
    public User getUser() {
        final User user = new User();
        user.setCreatedAt(LocalDate.now());
        user.setId(null);
        user.setRole(Role.USER);
        return user;
    }

    @GetMapping("/create")
    public String create() {
        return "signup";
    }

    @PostMapping("/create")
    public String createUser(@Valid User user, Model model) {
        try {
            userService.registerNewUser(user);
            return "redirect:/p4/account";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @ModelAttribute
    public ChangePasswordPayload getChangePasswordPayload() {
        return new ChangePasswordPayload("", "", "");
    }

    @PostMapping("/changePassword")
    public String changePassword(@Valid ChangePasswordPayload passwordPayload, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Access denied");
        }
        userService.updatePassword(authentication.getName(), passwordPayload);
        authentication.setAuthenticated(false);
        return "redirect:/p4/account";
    }
}
