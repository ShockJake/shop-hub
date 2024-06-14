package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.ShippingDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShippingDetailRepository extends MongoRepository<ShippingDetail, String> {
    ShippingDetail findByUserId(String customerId);
    List<ShippingDetail> findAllByUserId(String customerId);
}