package com.university.shophub.frontend.payloads;

import com.university.shophub.backend.models.TechnicalDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditProductPayload {
    private String productId;
    private String productName;
    private String sellerName;
    private String description;
    private Long Quantity;
    private Long category;
    private Long price;
    private MultipartFile imageFile;
    private List<TechnicalDetail> technicalDetails;
}
