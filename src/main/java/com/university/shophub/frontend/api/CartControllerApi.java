package com.university.shophub.frontend.api;

import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.CartService;
import com.university.shophub.backend.services.CategoryService;
import com.university.shophub.backend.services.UserService;
import com.university.shophub.frontend.payloads.AdjustProductsPayload;
import com.university.shophub.frontend.util.AccessViolationChecker;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/cart")
public record CartControllerApi(UserService userService, CartService cartService, CategoryService categoryService,
                                AccessViolationChecker accessViolationChecker) {


    @PatchMapping("/add/{id}/")
    public void addProducts(@PathVariable @NonNull String id, @RequestBody @NonNull AdjustProductsPayload payload,
                            Model model, Authentication authentication) {
        if (accessViolationChecker.isAccessViolated(authentication, false)) {
            throw new AccessDeniedException("Access denied");
        }

        log.debug("Receiving cart data: {}, {}", payload.getQuantity(), id);
        final User user = userService.getUserByEmail(authentication.getName());
        cartService.addProductsToCart(user.getId(), id, payload.getQuantity());
        model.addAttribute("products", cartService.getProducts(user.getId()));
        model.addAttribute("totalPrice", cartService.getTotalPrice(user.getId()));
        model.addAttribute("categories", categoryService.getAllCategories());
    }


    @PatchMapping("/remove/{id}")
    public void deleteProduct(@PathVariable @NonNull String id, @RequestBody @NonNull AdjustProductsPayload payload,
                              Model model, Authentication authentication) {
        if (accessViolationChecker.isAccessViolated(authentication, false)) {
            throw new AccessDeniedException("Access denied");
        }

        log.debug("Deleting cart data: {}, {}", payload.getQuantity(), id);

        final User user = userService.getUserByEmail(authentication.getName());
        cartService.deleteProductsFromCart(user.getId(), id, payload.getQuantity());
        model.addAttribute("products", cartService.getProducts(user.getId()));
        model.addAttribute("totalPrice", cartService.getTotalPrice(user.getId()));
        model.addAttribute("categories", categoryService.getAllCategories());
    }
}
