package com.university.shophub.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDetail {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String userEmail;
    private String phoneNumber;
    private String street;
    private String homeNumber;
    private String country;
    private String city;
    private String postalCode;
    List<Product> orderProducts;
}
