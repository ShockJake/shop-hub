package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Request;
import com.university.shophub.backend.models.RequestStatus;
import com.university.shophub.backend.repositories.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final RequestProcessor requestProcessor;

    public RequestService(RequestRepository requestRepository, RequestProcessor requestProcessor) {
        this.requestRepository = requestRepository;
        this.requestProcessor = requestProcessor;
    }

    @Transactional
    public Request saveRequest(Request request) {
        return this.requestRepository.save(request);
    }

    @Transactional
    public Request getRequestById(String id) {
        return this.requestRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Request with id " + id + " not found"));
    }

    @Transactional
    public Request getRequestByRequesterId(String requesterId) {
        return this.requestRepository.findByRequesterId(requesterId).orElseThrow(() ->
                new IllegalArgumentException("Request with requesterId " + requesterId + " not found"));
    }

    @Transactional
    public List<Request> getAllRequests() {
        return this.requestRepository.findAll();
    }

    @Transactional
    public Request mapRequestActionAndProcess(String id, String requestAction) {
        if ("approve".equals(requestAction)) {
            return this.approveRequest(id);
        } else if ("reject".equals(requestAction)) {
            return this.rejectRequest(id);
        }
        throw new IllegalArgumentException("Unknown request action " + requestAction);
    }

    private Request rejectRequest(String id) {
        Request request = this.requestRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Request with id " + id + " not found"));
        if (!request.getRequestStatus().equals(RequestStatus.PENDING)) {
            throw new IllegalArgumentException("Request with id " + id + " is already processed with status " + request.getRequestStatus());
        }
        request.setRequestStatus(RequestStatus.REJECTED);
        log.info("Request with id {} rejected", id);
        return this.requestRepository.save(request);
    }

    private Request approveRequest(String id) {
        Request request = this.requestRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Request with id " + id + " not found"));
        if (!request.getRequestStatus().equals(RequestStatus.PENDING)) {
            throw new IllegalArgumentException("Request with id " + id + " is already processed with status " + request.getRequestStatus());
        }
        log.info("Processing request with id: {}", id);
        requestProcessor.processRequest(request);
        request.setRequestStatus(RequestStatus.APPROVED);
        log.info("Approved request with id - {} and type - {}", id, request.getRequestType().name().toLowerCase());
        return this.requestRepository.save(request);
    }

    @Transactional
    public Request deleteRequest(String id) {
        Request request = requestRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Request with id " + id + " not found"));
        if (request.getRequestStatus().equals(RequestStatus.PENDING)) {
            throw new IllegalArgumentException("Cannot delete request with id " + id + " before processing it");
        }
        requestRepository.deleteById(id);
        request.setRequestStatus(RequestStatus.DELETED);
        return request;
    }
}
