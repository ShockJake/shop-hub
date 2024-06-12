package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String username);

    Optional<User> findByEmail(String email);
    List<User> findAllByNameContainingIgnoreCase(String name);
}
