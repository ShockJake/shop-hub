package com.university.shophub.frontend.util;

import com.university.shophub.backend.models.User;
import com.university.shophub.backend.services.UserService;
import com.university.shophub.frontend.provider.UserProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccessViolationCheckerTest {

    @Mock
    private UserService userService;

    private AccessViolationChecker accessViolationChecker;

    @BeforeEach
    void beforeEach() {
        accessViolationChecker = new AccessViolationChecker(userService);
    }

    @Test
    void shouldCheckAccessViolationWhenNoAuthentication() {
        // given
        final String idFromRequest = "TEST_ID";
        final Authentication authentication = new TestingAuthenticationToken(idFromRequest,
                UserProvider.passwordEncoder.encode(idFromRequest));
        authentication.setAuthenticated(false);

        // when
        final boolean result = accessViolationChecker.isAccessViolated(idFromRequest, authentication, false);

        // then
        assertTrue(result);
    }

    @Test
    void shouldCheckAccessViolationWhenUserIsNotAdmin() {
        // given
        final User user = UserProvider.getUser();
        final Authentication authentication = new TestingAuthenticationToken(user.getEmail(), user.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(user.getEmail())).willReturn(user);

        // when
        final boolean result = accessViolationChecker.isAccessViolated(user.getId(), authentication, true);

        // then
        assertTrue(result);
    }

    @Test
    void shouldCheckAccessViolationWhenUserIsAdmin() {
        // given
        final User admin = UserProvider.getAdmin();
        final Authentication authentication = new TestingAuthenticationToken(admin.getEmail(), admin.getPassword());
        authentication.setAuthenticated(true);
        given(userService.getUserByEmail(admin.getEmail())).willReturn(admin);

        // when
        final boolean result = accessViolationChecker.isAccessViolated(admin.getId(), authentication, true);

        // then
        assertFalse(result);
    }
}
