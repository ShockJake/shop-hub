package com.university.shophub.frontend.api;

import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.VisibleForTesting;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public record UserControllerAPI(UserService userService) {


    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id, Authentication authentication) {
        log.debug("Getting user by id {}", id);
        if (isAccessViolated(id, authentication, false)) {
            throw new AccessDeniedException("Access denied");
        }
        User user = userService.getUserById(id);
        user.setPassword("hidden");
        return user;
    }

    @GetMapping("")
    public List<User> getAllUsers(Authentication authentication) {
        log.debug("Getting all users");
        if (isAccessViolated(null, authentication, true)) {
            throw new AccessDeniedException("Access denied");
        }
        return userService.getAllUsers();
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable String id, @Validated @RequestBody User user, Authentication authentication) {
        log.info("Updating user with id {}", id);
        if (isAccessViolated(id, authentication, false)) {
            throw new AccessDeniedException("Access denied");
        }
        User updatedUser = userService.updateUser(id, user);
        updatedUser.setPassword("hidden");
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable String id, Authentication authentication) {
        log.debug("Deleting user with id {}", id);
        if (isAccessViolated(id, authentication, false)) {
            throw new AccessDeniedException("Access denied");
        }
        User deletedUser = userService.deleteUser(id);
        deletedUser.setPassword("hidden");
        if (deletedUser.getEmail().equals(authentication.getName())) {
            authentication.setAuthenticated(false);
        }
        return deletedUser;
    }

    @VisibleForTesting
    boolean isAccessViolated(String idFromRequest, Authentication authentication, boolean adminRequired) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return true;
        }
        final User user = userService.getUserByEmail(authentication.getName());
        if (Role.ADMIN.equals(user.getRole())) {
            return false;
        }
        return checkIdMismatch(user.getId(), idFromRequest) || adminRequired;
    }

    private boolean checkIdMismatch(String idFromDatabase, String idFromRequest) {
        return !idFromDatabase.equals(idFromRequest);
    }
}
