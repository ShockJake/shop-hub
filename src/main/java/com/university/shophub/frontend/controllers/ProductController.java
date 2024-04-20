package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.services.ProductService;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public record ProductController(ProductService productService) {

    @GetMapping("/{id}")
    public String getProductById(@PathVariable @NotNull @DecimalMin("0") long id, Model model) {
        productService.getProductById(id);
        model.addAttribute("product", productService.getProductById(id));
        return "product";
    }
}
