package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<Wallet, String> {
    Wallet findByUserId(String userID);
}
