package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Request;
import com.university.shophub.backend.models.RequestType;
import com.university.shophub.backend.models.Role;
import com.university.shophub.backend.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Component
public class RequestProcessor {
    private final Map<RequestType, Consumer<String>> processors = Map.of(
            RequestType.SELLER_ROLE_REQUEST, this::processSellerRoleRequest
    );

    private final UserService userService;

    public RequestProcessor(UserService userService) {
        this.userService = userService;
    }

    public void processRequest(Request request) {
        if (!processors.containsKey(request.getRequestType())) {
            throw new IllegalArgumentException("Unknown request type: " + request.getRequestType());
        }
        processors.get(request.getRequestType()).accept(request.getRequesterId());
    }

    private void processSellerRoleRequest(String requesterId) {
        log.info("Processing sellerRole request for requesterId: {}", requesterId);
        User user = userService.getUserById(requesterId);
        user.setRole(Role.SELLER);
        userService.updateUser(requesterId, user);
    }
}
