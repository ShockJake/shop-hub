package com.university.shophub.frontend.provider;

import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public class UserProvider {

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

    public static User getAdmin() {
        return User.builder()
                .id("TEST_ADMIN_ID")
                .name("ADMIN")
                .email("admin@admin.com")
                .password(passwordEncoder.encode("1234"))
                .role(Role.ADMIN)
                .createdAt(LocalDate.now())
                .build();
    }

    public static User getUser() {
        return User.builder()
                .id("TEST_USER_ID")
                .name("USER")
                .email("user@user.com")
                .password(passwordEncoder.encode("1234"))
                .role(Role.USER)
                .createdAt(LocalDate.now())
                .build();
    }
}
