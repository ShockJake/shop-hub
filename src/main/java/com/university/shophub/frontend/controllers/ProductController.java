package com.university.shophub.frontend.controllers;

import com.google.common.collect.Streams;
import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.models.TechnicalDetail;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.CategoryService;
import com.university.shophub.backend.services.ProductService;
import com.university.shophub.backend.services.UserService;
import com.university.shophub.frontend.payloads.CreateProductPayload;
import com.university.shophub.frontend.payloads.EditProductPayload;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public String getProductById(@PathVariable @NotNull String id, Model model, Authentication authentication) {
        final Product product = productService.getProductById(id);
        User seller = userService.getUserByEmail(product.getSellerName());

        log.trace("Found product with id {}", product);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("sellerId", seller.getId());
        model.addAttribute("sellerName", seller.getName());
        model.addAttribute("isOwner", seller.getEmail().equals(authentication.getName()));
        return "product";
    }

    @GetMapping("/categories/{category}")
    public String getProductByCategory(@PathVariable @NotNull String category, Model model) {
        List<Product> products = productService.getProductByCategory(StringUtils.capitalize(category));
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("currentCategory", category);

        return "products";
    }

    @GetMapping("/create/new")
    public String createProduct(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", new Product());
        return "create-product";
    }

    @PostMapping(path = "/create/new", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String saveNewProduct(@ModelAttribute CreateProductPayload productPayload,
                                 @RequestParam(value = "technicalDetail[]") String[] technicalDetails,
                                 @RequestParam(value = "detailDescription[]") String[] detailDescriptions,
                                 Authentication authentication) {
        List<TechnicalDetail> technicalDetail = Streams.zip(Arrays.stream(technicalDetails), Arrays.stream(detailDescriptions), TechnicalDetail::new).toList();
        productService.addProduct(productPayload, technicalDetail, authentication.getName());
        return "redirect:/p4/product/create/new?productCreated";
    }

    @GetMapping("/edit/{productId}")
    public String editProduct(@PathVariable @NotNull String productId, Model model) {
        Product productById = productService.getProductById(productId);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("imageUrl", productById.getImageUrl());
        model.addAttribute("editProductPayload",
                new EditProductPayload(productById.getId(),
                        productById.getName(),
                        productById.getSellerName(),
                        productById.getDescription(),
                        productById.getQuantity(),
                        productById.getCategoryId(),
                        productById.getPrice().longValue(),
                        null,
                        productById.getTechnicalDetails()));
        return "edit-product";
    }

    @ModelAttribute
    public CreateProductPayload getCreateProductPayload() {
        return new CreateProductPayload("", "", null, null, null);
    }
}
