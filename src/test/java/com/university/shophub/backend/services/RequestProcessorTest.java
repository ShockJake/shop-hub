package com.university.shophub.backend.services;

import com.university.shophub.backend.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RequestProcessorTest {

    @Mock
    private UserService userService;

    private RequestProcessor requestProcessor;

    @BeforeEach
    void beforeEach() {
        requestProcessor = new RequestProcessor(userService);
    }

    @Test
    void shouldMapAndProcessSellerRoleRequest() {
        // given
        final String requesterId = "SOME_REQUESTER_ID";
        final User user = User.builder().build();
        final Request request = Request.builder()
                .id("SOME_REQUEST_ID")
                .requesterId(requesterId)
                .requesterUserName("SOME_USERNAME")
                .requestMessage("SOME_REQUEST_MESSAGE")
                .requestStatus(RequestStatus.PENDING)
                .requestType(RequestType.SELLER_ROLE_REQUEST)
                .build();
        given(userService.getUserById(requesterId)).willReturn(user);

        // when
        requestProcessor.processRequest(request);

        // then
        assertEquals(Role.SELLER, user.getRole());
        verify(userService).updateUser(requesterId, user);
    }
}
