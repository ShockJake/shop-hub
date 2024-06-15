package com.university.shophub.frontend.api;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@RequestMapping("/api/products")
public record ProductControllerAPI(ProductService productService) {

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        log.info("Get product by id: {}", id);
        return productService.getProductById(id);
    }

    @PatchMapping()
    public RedirectView updateProduct(@RequestBody Product product) {
        log.info("Updating product with body: {}", product.getId());
        productService.updateProduct(product);
        return new RedirectView("/product/" + product.getId() + "?productUpdated");
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        log.debug("Deleting product with id: {}", id);
        productService.deleteProductById(id);
    }
}
