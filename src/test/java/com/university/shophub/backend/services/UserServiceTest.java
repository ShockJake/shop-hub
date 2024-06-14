package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.models.Wallet;
import com.university.shophub.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CartService cartService;
    @Mock
    private WalletService walletService;

    private UserService userService;

    @BeforeEach
    void beforeEach() {
        userService = new UserService(userRepository, cartService, walletService);
    }

    @Test
    void shouldRegisterNewUser() {
        // given
        User user = getProperUser();
        Wallet wallet = Wallet.builder()
                .userId(user.getId())
                .history(new ArrayList<>())
                .balance(0.0)
                .build();
        given(userRepository.save(any(User.class))).willReturn(user);
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());

        // when
        User newUser = userService.registerNewUser(user);

        // then
        assertEquals(user, newUser);
        verify(walletService).createWallet(wallet);
    }

    @Test
    void shouldUpdateUserData() {
        // given
        User baseUser = getProperUser();
        User userWithDataToUpdate = getProperUser();
        userWithDataToUpdate.setName("NEW_USER_NAME");
        userWithDataToUpdate.setPassword("hidden");

        User updatedUser = getProperUser();
        updatedUser.setPassword(baseUser.getPassword());
        updatedUser.setName(userWithDataToUpdate.getName());
        given(userRepository.findById(baseUser.getId())).willReturn(Optional.of(baseUser));
        given(userRepository.save(updatedUser)).willReturn(updatedUser);

        // when
        User result = userService.updateUser(userWithDataToUpdate.getId(), userWithDataToUpdate);

        // then
        assertEquals(updatedUser, result);
        verify(userRepository).save(updatedUser);
    }

    @Test
    void shouldDeleteUser() {
        // given
        final User expectedUser = getProperUser();
        given(userRepository.findById(expectedUser.getId())).willReturn(Optional.of(expectedUser));

        // when
        final User actualUser = userService.deleteUser(expectedUser.getId());

        // then
        assertEquals(expectedUser, actualUser);
        verify(userRepository).delete(expectedUser);
        verify(cartService).deleteCart(expectedUser.getId());
    }

    @Test
    void shouldThrowExceptionIfUserNotFound() {
        // given
        given(userRepository.findByEmail(any(String.class))).willReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> userService.getUserByEmail("TEST_EMAIL"));
    }

    @Test
    void shouldGetOnlySellers() {
        // given
        final User seller = getProperUser();
        seller.setRole(Role.SELLER);
        seller.setName("Seller");
        List<User> users = List.of(getProperUser(), seller);
        given(userRepository.findAllByNameContainingIgnoreCase(any(String.class))).willReturn(users);

        // when
        List<User> sellers = userService.getSellers(seller.getName());

        // then
        assertEquals(1, sellers.size());
        assertEquals(seller, sellers.getFirst());
    }

    private User getProperUser() {
        return User.builder()
                .id("TEST_USER_ID")
                .name("TEST_USER_NAME")
                .role(Role.USER)
                .createdAt(LocalDate.of(2024, 4, 21))
                .email("TEST_EMAIL")
                .password("TEST_PASSWORD")
                .build();
    }
}
