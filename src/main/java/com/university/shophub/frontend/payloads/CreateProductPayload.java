package com.university.shophub.frontend.payloads;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductPayload {
    private String name;
    private String description;
    private Long category;
    private BigDecimal price;
    private MultipartFile imageFile;
}
