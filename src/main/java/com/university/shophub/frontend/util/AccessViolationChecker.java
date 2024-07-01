package com.university.shophub.frontend.util;

import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public record AccessViolationChecker(UserService userService) {

    public boolean isAccessViolated(String userIdFromRequest, Authentication authentication, boolean adminRequired) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return true;
        }
        final User user = userService.getUserByEmail(authentication.getName());
        if (Role.ADMIN.equals(user.getRole())) {
            return false;
        }
        return checkIdMismatch(user.getId(), userIdFromRequest) || adminRequired;
    }

    public boolean isAccessViolated(Authentication authentication, boolean adminRequired) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return true;
        }
        final User user = userService.getUserByEmail(authentication.getName());
        if (Role.ADMIN.equals(user.getRole())) {
            return false;
        }
        return adminRequired;
    }

    private boolean checkIdMismatch(String idFromDatabase, String idFromRequest) {
        return !idFromDatabase.equals(idFromRequest);
    }
}
