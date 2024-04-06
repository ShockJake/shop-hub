package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Long> {
    Category findByName(String name);
}
