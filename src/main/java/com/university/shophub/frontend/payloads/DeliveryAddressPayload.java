package com.university.shophub.frontend.payloads;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressPayload {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String postalCode;
    private String country;
    private String street;
    private String city;
    private String homeNumber;
}
