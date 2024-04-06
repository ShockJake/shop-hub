package com.university.shophub.backend.models;

import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    private String email;
    private String password;
    private Role role;
}
