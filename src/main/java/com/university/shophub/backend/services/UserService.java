package com.university.shophub.backend.services;

import com.university.shophub.backend.models.User;
import com.university.shophub.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepository userRepository) {
    public User registerNewUser(User newUser) {
        return userRepository.save(newUser);
    }
}
