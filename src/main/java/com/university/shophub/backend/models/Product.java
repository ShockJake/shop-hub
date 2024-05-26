package com.university.shophub.backend.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    private String id;
    @NotBlank(message = "Seller name cannot be empty")
    private String sellerName;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    @NotBlank(message = "Category id cannot be empty")
    private Long categoryId;
    @NotBlank(message = "Price cannot be empty")
    private BigDecimal price;
    @NotBlank(message = "Image url cannot be empty")
    private String imageUrl;
    private List<TechnicalDetail> technicalDetails;
}
