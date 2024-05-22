package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, Long>
{
    Cart findByUserId(String userId);
}
