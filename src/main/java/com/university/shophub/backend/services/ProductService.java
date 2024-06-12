package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Category;
import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.models.TechnicalDetail;
import com.university.shophub.backend.repositories.ProductRepository;
import com.university.shophub.frontend.payloads.CreateProductPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public record ProductService(ProductRepository productRepository, CategoryService categoryService) {

    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/uploads";

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product addProduct(CreateProductPayload productPayload, List<TechnicalDetail> technicalDetails, String userName) {
        MultipartFile imageFile = productPayload.getImageFile();
        String uniqueFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

        Path uploadPath = Path.of(UPLOAD_DIRECTORY);
        Path filePath = uploadPath.resolve(uniqueFileName);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }

        Product product = Product
                .builder()
                .name(productPayload.getName())
                .sellerName(userName)
                .price(productPayload.getPrice())
                .description(productPayload.getDescription())
                .categoryId(productPayload.getCategory())
                .imageUrl("/uploads/" + uniqueFileName)
                .technicalDetails(technicalDetails)
                .build();

        log.info("new product registered: {}", product);

        Product save = productRepository.save(product);
        log.info("new product saved: {}", save);
        return save;
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductContainingName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductByCategory(String categoryName) {
        List<Category> categories = categoryService.getAllCategories();
        return productRepository.findByCategoryId(categoryService.getCategoryByName(categoryName).getId());

    }

    public void saveAll(List<Product> initialProducts) {
        productRepository.saveAll(initialProducts);
    }
}
