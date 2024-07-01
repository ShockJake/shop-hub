package com.university.shophub.frontend.api;

import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.UserService;
import com.university.shophub.frontend.provider.UserProvider;
import com.university.shophub.frontend.util.AccessViolationChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserControllerApiTest {

    @Mock
    private static UserService userService;

    private static UserControllerAPI userControllerAPI;


    @BeforeEach
    void beforeEach() {
        userControllerAPI = new UserControllerAPI(userService, new AccessViolationChecker(userService));
    }

    @Test
    void shouldGetAllUsers() {
        // given
        final User admin = UserProvider.getAdmin();
        final Authentication authentication = new TestingAuthenticationToken(admin.getEmail(), admin.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(admin.getEmail())).willReturn(admin);
        given(userService.getAllUsers()).willReturn(List.of(UserProvider.getUser()));

        // when
        final List<User> users = userControllerAPI.getAllUsers(authentication);

        // then
        assertEquals(1, users.size());
    }

    @Test
    void shouldNotGetAllUsersDueToAccessViolation() {
        // given
        final User user = UserProvider.getUser();
        final Authentication authentication = new TestingAuthenticationToken(user.getEmail(), user.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(user.getEmail())).willReturn(user);

        // when & then
        assertThrows(AccessDeniedException.class, () -> userControllerAPI.getAllUsers(authentication));
    }

    @Test
    void shouldGetUserById() {
        // given
        final User expectedUser = UserProvider.getUser();
        final Authentication authentication = new TestingAuthenticationToken(expectedUser.getEmail(), expectedUser.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(expectedUser.getEmail())).willReturn(expectedUser);
        given(userService.getUserById(expectedUser.getId())).willReturn(expectedUser);

        // when
        final User actualUser = userControllerAPI.getUserById(expectedUser.getId(), authentication);

        // then
        assertEquals(expectedUser.getId(), actualUser.getId());
    }
}
