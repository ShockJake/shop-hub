package com.university.shophub.frontend.api;

import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private static UserService userService;

    private static UserController userController;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

    @BeforeEach
    void beforeEach() {
        userController = new UserController(userService);
    }

    @Test
    void shouldGetAllUsers() {
        // given
        final User admin = getAdmin();
        final Authentication authentication = new TestingAuthenticationToken(admin.getEmail(), admin.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(admin.getEmail())).willReturn(admin);
        given(userService.getAllUsers()).willReturn(List.of(getUser()));

        // when
        final List<User> users = userController.getAllUsers(authentication);

        // then
        assertEquals(1, users.size());
    }

    @Test
    void shouldNotGetAllUsersDueToAccessViolation() {
        // given
        final User user = getUser();
        final Authentication authentication = new TestingAuthenticationToken(user.getEmail(), user.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(user.getEmail())).willReturn(user);

        // when & then
        assertThrows(AccessDeniedException.class, () -> userController.getAllUsers(authentication));
    }

    @Test
    void shouldGetUserById() {
        // given
        final User expectedUser = getUser();
        final Authentication authentication = new TestingAuthenticationToken(expectedUser.getEmail(), expectedUser.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(expectedUser.getEmail())).willReturn(expectedUser);
        given(userService.getUserById(expectedUser.getId())).willReturn(expectedUser);

        // when
        final User actualUser = userController.getUserById(expectedUser.getId(), authentication);

        // then
        assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test
    void shouldCheckAccessViolationWhenNoAuthentication() {
        // given
        final String idFromRequest = "TEST_ID";
        final Authentication authentication = new TestingAuthenticationToken(idFromRequest, passwordEncoder.encode(idFromRequest));
        authentication.setAuthenticated(false);

        // when
        final boolean result = userController.isAccessViolated(idFromRequest, authentication, false);

        // then
        assertTrue(result);
    }

    @Test
    void shouldCheckAccessViolationWhenUserIsNotAdmin() {
        // given
        final User user = getUser();
        final Authentication authentication = new TestingAuthenticationToken(user.getEmail(), user.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(user.getEmail())).willReturn(user);

        // when
        final boolean result = userController.isAccessViolated(user.getId(), authentication, true);

        // then
        assertTrue(result);
    }

    @Test
    void shouldCheckAccessViolationWhenUserIsAdmin() {
        // given
        final User admin = getAdmin();
        final Authentication authentication = new TestingAuthenticationToken(admin.getEmail(), admin.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(admin.getEmail())).willReturn(admin);

        // when
        final boolean result = userController.isAccessViolated(admin.getId(), authentication, true);

        // then
        assertFalse(result);
    }

    private User getAdmin() {
        return User.builder()
                .id("TEST_ADMIN_ID")
                .name("ADMIN")
                .email("admin@admin.com")
                .password(passwordEncoder.encode("1234"))
                .role(Role.ADMIN)
                .createdAt(LocalDate.now())
                .build();
    }

    private User getUser() {
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
