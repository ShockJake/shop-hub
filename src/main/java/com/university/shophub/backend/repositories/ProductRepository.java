package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
    List<Product> findByCategoryId(Long id);
}
