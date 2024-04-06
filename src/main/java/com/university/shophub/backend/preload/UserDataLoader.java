package com.university.shophub.backend.preload;

import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataLoader implements ApplicationRunner {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
    private final UserRepository userRepository;

    public UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() == 0) {
            List<User> initialUsers = List.of(new User(1L, "SuperAdmin", "admin@admin.com",
                    passwordEncoder.encode("1234"), Role.ADMIN));
            userRepository.saveAll(initialUsers);
        }
    }
}
