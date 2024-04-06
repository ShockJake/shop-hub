package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, Long> {
    User findByName(String username);
}
