package com.university.shophub.frontend.payloads;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressPayload {
    private String firstName;
    private String lastName;
    private String userName;
    private String userEmail;
    private String phoneNumber;
    private String street;
    private String homeNumber;
    private String country;
    private String city;
    private String postalCode;
}
