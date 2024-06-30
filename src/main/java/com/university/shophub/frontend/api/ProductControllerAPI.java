package com.university.shophub.frontend.api;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public Product updateProduct(@RequestBody Product product) {
        log.info("Updating product with body: {}", product.getId());
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        log.debug("Deleting product with id: {}", id);
        productService.deleteProductById(id);
    }
}
