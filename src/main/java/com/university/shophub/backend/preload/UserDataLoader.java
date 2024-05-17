package com.university.shophub.backend.preload;

import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
            List<User> initialUsers = List.of(new User(String.valueOf(UUID.randomUUID()), "SuperAdmin", "admin@admin.com",
                            passwordEncoder.encode("1234"), Role.ADMIN, LocalDate.now()),
                    new User(String.valueOf(UUID.randomUUID()), "SuperSeller", "seller@seller.com",
                            passwordEncoder.encode("1234"), Role.SELLER, LocalDate.now()),
                    new User(String.valueOf(UUID.randomUUID()), "SuperUser", "user@user.com",
                            passwordEncoder.encode("1234"), Role.USER, LocalDate.now())
            );
            userRepository.saveAll(initialUsers);
        }
    }
}
