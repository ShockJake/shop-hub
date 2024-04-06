package com.university.shophub.frontend.security;

import com.university.shophub.backend.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopHubUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ShopHubUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final com.university.shophub.backend.models.User user =
                Optional.ofNullable(userRepository.findByUserName(username))
                        .orElseThrow(() -> new UsernameNotFoundException(username));
        return User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
