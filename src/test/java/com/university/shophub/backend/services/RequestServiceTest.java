package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Request;
import com.university.shophub.backend.models.RequestStatus;
import com.university.shophub.backend.models.RequestType;
import com.university.shophub.backend.repositories.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RequestServiceTest {

    @Mock
    private RequestRepository requestRepository;
    @Mock
    private RequestProcessor requestProcessor;

    private RequestService requestService;


    @BeforeEach
    void beforeEach() {
        requestService = new RequestService(requestRepository, requestProcessor);
    }

    @Test
    void shouldSaveNewRequest() {
        // given
        final Request request = getProperRequest();

        // when
        requestService.saveRequest(request);

        // then
        verify(requestRepository).save(request);
    }

    @Test
    void shouldFindRequestById() {
        // given
        final Request expectedRequest = getProperRequest();
        given(requestRepository.findById(expectedRequest.getId())).willReturn(Optional.of(expectedRequest));

        // when
        final Request actualRequest = requestService.getRequestById(expectedRequest.getId());

        // then
        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    void shouldFindRequestByRequesterId() {
        // given
        final Request expectedRequest = getProperRequest();
        given(requestRepository.findByRequesterId(expectedRequest.getRequesterId())).willReturn(Optional.of(expectedRequest));

        // when
        final Request actualRequest = requestService.getRequestByRequesterId(expectedRequest.getRequesterId());

        // then
        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    void shouldGetAllRequests() {
        // given
        final Request expectedRequest = getProperRequest();
        given(requestRepository.findAll()).willReturn(List.of(expectedRequest));

        // when
        final List<Request> requests = requestService.getAllRequests();
        final Request actualRequest = requests.getFirst();

        // then
        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    void shouldApproveRequest() {
        // given
        final Request request = getProperRequest();
        given(requestRepository.findById(request.getId())).willReturn(Optional.of(request));
        given(requestRepository.save(request)).willReturn(request);

        // when
        final Request result = requestService.mapRequestActionAndProcess(request.getId(), "approve");

        // then
        assertEquals(RequestStatus.APPROVED, request.getRequestStatus());
        verify(requestRepository).save(result);
    }


    @Test
    void shouldRejectRequest() {
        // given
        final Request request = getProperRequest();
        given(requestRepository.findById(request.getId())).willReturn(Optional.of(request));
        given(requestRepository.save(request)).willReturn(request);

        // when
        final Request result = requestService.mapRequestActionAndProcess(request.getId(), "reject");

        // then
        assertEquals(RequestStatus.REJECTED, request.getRequestStatus());
        verify(requestRepository).save(result);
    }

    @Test
    void shouldThrowExceptionIfActionIsUnknown() {
        // given
        final String unknownAction = "unknown action";
        final Request request = getProperRequest();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> requestService.mapRequestActionAndProcess(request.getId(), unknownAction));
    }

    @Test
    void shouldThrowExceptionIfRequestIsNotFound() {
        // given
        final Request request = getProperRequest();
        final String action = "approve";
        given(requestRepository.findById(request.getId())).willReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> requestService.mapRequestActionAndProcess(request.getId(), action));
    }

    @Test
    void shouldThrowExceptionIfProcessingAlreadyProcessedRequest() {
        // given
        final Request request = getProperRequest();
        request.setRequestStatus(RequestStatus.APPROVED);
        final String action = "approve";
        given(requestRepository.findById(request.getId())).willReturn(Optional.of(request));

        // when & then
        assertThrows(IllegalArgumentException.class, () -> requestService.mapRequestActionAndProcess(request.getId(), action));
    }

    @Test
    void shouldDeleteRequest() {
        // given
        final Request request = getProperRequest();
        request.setRequestStatus(RequestStatus.APPROVED);
        given(requestRepository.findById(request.getId())).willReturn(Optional.of(request));

        // when
        requestService.deleteRequest(request.getId());

        // then
        assertEquals(RequestStatus.DELETED, request.getRequestStatus());
        verify(requestRepository).deleteById(request.getId());
    }

    @Test
    void shouldThrowExceptionWhenDeletingUnprocessedRequest() {
        // given
        final Request request = getProperRequest();
        given(requestRepository.findById(request.getId())).willReturn(Optional.of(request));

        // when & then
        assertThrows(IllegalArgumentException.class, () -> requestService.deleteRequest(request.getId()));
    }

    private Request getProperRequest() {
        return Request.builder()
                .id("SOME_REQUEST_ID")
                .requesterId("SOME_REQUEST_ID")
                .requesterUserName("SOME_USERNAME")
                .requestMessage("SOME_REQUEST_MESSAGE")
                .requestStatus(RequestStatus.PENDING)
                .requestType(RequestType.SELLER_ROLE_REQUEST)
                .build();
    }
}
