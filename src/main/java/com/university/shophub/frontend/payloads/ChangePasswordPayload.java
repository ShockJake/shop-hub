package com.university.shophub.frontend.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordPayload {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
