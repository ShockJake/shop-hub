package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProductService(ProductRepository productRepository, CategoryService categoryService) {
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductContainingName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategoryId(categoryService.getCategoryByName(category).getId());
    }
}
