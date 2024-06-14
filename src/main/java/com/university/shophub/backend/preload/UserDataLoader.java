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
            List<User> initialUsers = List.of(new User("1", "SuperAdmin", "admin@admin.com",
                            passwordEncoder.encode("1234"), Role.ADMIN, LocalDate.now()),
                    new User("2", "SuperSeller", "seller@seller.com",
                            passwordEncoder.encode("1234"), Role.SELLER, LocalDate.now()),
                    new User("3", "SuperUser", "user@user.com",
                            passwordEncoder.encode("1234"), Role.USER, LocalDate.now()),
                    new User("4", "Premium Watches", "premiumWatches@shop.com",
                            passwordEncoder.encode("1234"), Role.SELLER, LocalDate.now()),
                    new User("5", "Home and Garden", "homeAndGarden@shop.com",
                            passwordEncoder.encode("1234"), Role.SELLER, LocalDate.now()),
                    new User("6", "GuitarShop", "guitar@shop.com",
                            passwordEncoder.encode("1234"), Role.SELLER, LocalDate.now()),
                    new User("7", "OutdoorShop", "outdoor@shop.com",
                            passwordEncoder.encode("1234"), Role.SELLER, LocalDate.now()),
                    new User("8", "TShirts", "tshirts@shop.com",
                            passwordEncoder.encode("1234"), Role.SELLER, LocalDate.now())
            );
            userRepository.saveAll(initialUsers);
        }
    }
}
