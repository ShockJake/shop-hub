package com.university.shophub.backend.repositories;

import com.university.shophub.backend.models.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RequestRepository extends MongoRepository<Request, String> {
    Optional<Request> findByRequesterId(String requesterId);
}
