package com.example.StudyFlow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentLeadRequest {

    @NotBlank(message = "Institute slug is required")
    private String instituteSlug;

    @NotBlank(message = "Student name is required")
    private String studentName;

    @NotBlank(message = "Parent phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be between 10 to 15 digits")
    private String parentPhone;

    private String targetClass;
    private String message;
}

