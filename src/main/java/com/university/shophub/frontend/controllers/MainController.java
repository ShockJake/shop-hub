package com.university.shophub.frontend.controllers;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.CategoryService;
import com.university.shophub.backend.services.ProductService;
import com.university.shophub.backend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
public record MainController(ProductService productService, CategoryService categoryService, UserService userService) {

    @RequestMapping({"/", "/home"})
    public String indexPage(Model model) {
        List<Product> products = productService.getAllProducts();
        Collections.shuffle(products);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("topProducts", products.subList(0, Math.min(products.size(), 9)));
        return "index";
    }

    @RequestMapping("/search")
    public String searchPage(Model model, @RequestParam(name = "search") String searchPrompt) {
        log.debug("Searing by prompt: {}", searchPrompt);

        final List<Product> foundProducts = productService.getProductContainingName(searchPrompt);
        log.debug("Found {} products", foundProducts.size());

        final List<User> searchedSellers = userService.getSellers(searchPrompt);
        log.debug("Found {} sellers", searchedSellers.size());

        model.addAttribute("foundSellers", searchedSellers);
        model.addAttribute("foundProducts", foundProducts);
        model.addAttribute("searchPrompt", searchPrompt);
        model.addAttribute("categories", categoryService.getAllCategories());

        if (searchedSellers.isEmpty() && foundProducts.isEmpty()) {
            model.addAttribute("footerClass", "fixed-bottom");
        }
        return "search-page";
    }
}
