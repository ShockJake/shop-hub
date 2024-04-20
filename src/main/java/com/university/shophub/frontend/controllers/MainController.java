package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.services.CategoryService;
import com.university.shophub.backend.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public record MainController(ProductService productService, CategoryService categoryService) {

    @RequestMapping({"/", "/home"})
    public String indexPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("topProducts", productService.getAllProducts());
        return "index";
    }
}
