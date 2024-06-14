package com.university.shophub.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    private String id;
    private LocalDate purchaseDate;
    private String sellerId;
    private String userId;
    private String sellerName;
    private List<String> productNames;
}
