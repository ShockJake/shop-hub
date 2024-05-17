package com.university.shophub.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Indexed(unique = true)
    private String id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Indexed(unique = true)
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    private Role role;
    private LocalDate createdAt;
}
