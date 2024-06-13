package com.university.shophub.frontend.controllers;

import com.google.common.collect.Streams;
import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.models.TechnicalDetail;
import com.university.shophub.backend.services.CategoryService;
import com.university.shophub.backend.services.ProductService;
import com.university.shophub.backend.services.UserService;
import com.university.shophub.frontend.payloads.CreateProductPayload;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
public record ProductController(ProductService productService, CategoryService categoryService,
                                UserService userService) {

    @GetMapping("/{id}")
    public String getProductById(@PathVariable @NotNull String id, Model model) {
        Product productById = productService.getProductById(id);
        log.trace("Found product with id {}", productById);
        model.addAttribute("product", productById);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product";
    }

    @GetMapping("/categories/{category}")
    public String getProductByCategory(@PathVariable @NotNull String category, Model model) {
        List<Product> products = productService.getProductByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products";
    }

    @GetMapping("/create/new")
    public String createProduct(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", new Product());
        return "create-product";
    }

    @ModelAttribute
    public CreateProductPayload getCreateProductPayload() {
        return new CreateProductPayload("", "", null, null, null);
    }

    @PostMapping(path = "/create/new", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveNewProduct(@ModelAttribute CreateProductPayload productPayload,
                                 @RequestParam(value = "technicalDetail[]") String[] technicalDetails,
                                 @RequestParam(value = "detailDescription[]") String[] detailDescriptions,
                                 Authentication authentication) {
        List<TechnicalDetail> technicalDetail = Streams.zip(Arrays.stream(technicalDetails), Arrays.stream(detailDescriptions), TechnicalDetail::new).toList();
        productService.addProduct(productPayload, technicalDetail, authentication.getName());
        return "redirect:/product/create/new?productCreated";
    }

    @GetMapping("/edit/{id}")
    public String getEditProduct(Model model, @PathVariable String id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("technicalDetails", product.getTechnicalDetails());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit-product";
    }

    @PutMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable String id) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "redirect:product";
    }
}
