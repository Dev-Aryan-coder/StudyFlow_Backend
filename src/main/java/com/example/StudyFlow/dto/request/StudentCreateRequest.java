package com.example.StudyFlow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StudentCreateRequest {

    @NotBlank(message = "Project ID is required")
    private String projectId;

    private String batchId;

    @NotBlank(message = "Student name is required")
    private String name;

    private String parentName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$")
    private String phoneNumber;

    private String email;
    private String rollNumber;
    private BigDecimal pendingFees = BigDecimal.ZERO;
}
