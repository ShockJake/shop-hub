package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(User newUser) {

        return userRepository.save(newUser);
    }

    @Transactional
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User with id " + id + " not found"));
    }

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByName(username).orElseThrow(() ->
                new IllegalArgumentException("User with name " + username + " not found"));
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(long id, User user) {
        userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User with id " + id + " not found"));
        return userRepository.save(user);
    }

    @Transactional
    public User deleteUser(long id) {
        final User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User with id " + id + " not found"));
        userRepository.delete(user);
        return user;
    }
}
