package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.models.ShippingDetail;
import com.university.shophub.backend.repositories.ShippingDetailRepository;
import com.university.shophub.frontend.payloads.DeliveryAddressPayload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingDetailService {
    private final UserService userService;
    private final ProductService productService;
    private final ShippingDetailRepository repository;

    public ShippingDetailService(UserService userService, ProductService productService, ShippingDetailRepository repository) {
        this.userService = userService;
        this.productService = productService;
        this.repository = repository;
    }

    public void saveDetails(DeliveryAddressPayload shippingDetail, List<Product> products, String userId) {
        repository.save(
                ShippingDetail
                        .builder()
                        .userId(userId)
                        .firstName(shippingDetail.getFirstName())
                        .lastName(shippingDetail.getLastName())
                        .userName(shippingDetail.getUserName())
                        .userEmail(shippingDetail.getUserEmail())
                        .phoneNumber(shippingDetail.getPhoneNumber())
                        .city(shippingDetail.getCity())
                        .country(shippingDetail.getCountry())
                        .homeNumber(shippingDetail.getPhoneNumber())
                        .postalCode(shippingDetail.getPostalCode())
                        .orderProducts(products)
                        .build());
    }
}
