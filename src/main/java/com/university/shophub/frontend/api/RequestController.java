package com.university.shophub.frontend.api;

import com.university.shophub.backend.models.Request;
import com.university.shophub.backend.services.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/requests")
public record RequestController(RequestService requestService) {
    @GetMapping
    public List<Request> getRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public Request getRequest(@PathVariable String id) {
        return requestService.getRequestById(id);
    }

    @PostMapping
    public Request createRequest(@RequestBody Request request) {
        log.info("Creating request: {}", request);
        return requestService.saveRequest(request);
    }

    @PutMapping("/{id}/")
    public Request updateRequest(@PathVariable String id, @RequestParam(name = "requestAction") String requestAction) {
        return requestService.mapRequestActionAndProcess(id, requestAction);
    }

    @DeleteMapping("/{id}")
    public Request deleteRequest(@PathVariable String id) {
        return requestService.deleteRequest(id);
    }
}
