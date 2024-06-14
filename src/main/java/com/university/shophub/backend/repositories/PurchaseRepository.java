package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, String> {
    List<Purchase> getAllByUserId(String id);

    List<Purchase> findTop4ByUserIdOrderByPurchaseDateDesc(String id);
}
