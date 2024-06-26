package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.models.Wallet;
import com.university.shophub.backend.repositories.UserRepository;
import com.university.shophub.frontend.payloads.ChangePasswordPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CartService cartService;
    private final WalletService walletService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

    public UserService(UserRepository userRepository, CartService cartService, WalletService walletService) {
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.walletService = walletService;
    }

    public User registerNewUser(User newUser) {
        log.debug("Registering new user: '{}', '{}'", newUser.getName(), newUser.getEmail());
        userRepository.findByEmail(newUser.getEmail()).ifPresent(user -> {
            throw new ValidationException("User already exists");
        });
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User savedUser = userRepository.save(newUser);
        cartService.addCart(savedUser.getId());

        walletService.createWallet(Wallet.builder()
                .userId(savedUser.getId())
                .history(new ArrayList<>())
                .balance(0.0)
                .build());

        return savedUser;
    }

    @Transactional
    public User getUserById(String id) {
        log.debug("Retrieving user by id: '{}'", id);
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User with id " + id + " not found"));
    }

    @Transactional
    public User getUserByUsername(String username) {
        log.debug("Retrieving user by username: '{}'", username);
        return userRepository.findByName(username).orElseThrow(() ->
                new IllegalArgumentException("User with name " + username + " not found"));
    }

    @Transactional
    public List<User> getAllUsers() {
        log.debug("Retrieving all users");
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(String id, User user) {
        log.debug("Updating user with id: '{}'", id);
        User userToUpdate = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User with id " + id + " not found"));
        if ("hidden".equals(user.getPassword())) {
            user.setPassword(userToUpdate.getPassword());
        }
        return userRepository.save(user);
    }

    @Transactional
    public User deleteUser(String id) {
        log.debug("Deleting user with id: '{}'", id);
        final User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User with id " + id + " not found"));
        cartService.deleteCart(user.getId());
        userRepository.delete(user);
        return user;
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("User with email " + email + " not found"));
    }

    public void updatePassword(String userName, ChangePasswordPayload passwordPayload) {
        final User user = userRepository.findByEmail(userName).orElseThrow(() ->
                new IllegalArgumentException("User with email " + userName + " not found"));
        if (!passwordEncoder.matches(passwordPayload.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
        if (passwordPayload.getCurrentPassword().equals(passwordPayload.getNewPassword())) {
            throw new IllegalArgumentException("New password must be different than the old one");
        }
        if (!passwordPayload.getNewPassword().equals(passwordPayload.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        log.debug("Updating password for user {}", user.getName());
        user.setPassword(passwordEncoder.encode(passwordPayload.getNewPassword()));
        userRepository.save(user);
    }

    public List<User> getSellers(String userName) {
        log.debug("Searching sellers by user name: {}", userName);
        final List<User> result = userRepository.findAllByNameContainingIgnoreCase(userName);
        return result.stream()
                .filter(user -> Role.SELLER.equals(user.getRole()))
                .toList();
    }
}
