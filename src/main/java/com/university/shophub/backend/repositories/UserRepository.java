package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByUserName(String username);

}
