package com.example.StudyFlow.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicLoginRequest {

    /**
     * Accepts Student ID (Roll Number), Email, or Mobile Phone Number interchangeably
     */
    @NotBlank(message = "Identifier (Student ID, Email, or Phone) is required")
    private String identifier;

    @NotBlank(message = "Password is required")
    private String password;
}
