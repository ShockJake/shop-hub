package com.university.shophub.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    private String id;
    private String requestMessage;
    private String requesterId;
    private String requesterUserName;
    private RequestType requestType;
    private RequestStatus requestStatus;
}
