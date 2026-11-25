package com.example.StudyFlow.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomFeatureRequestDto {

    @NotBlank(message = "Coaching institute name is required")
    @Size(max = 150, message = "Institute name cannot exceed 150 characters")
    private String instituteName;

    @NotBlank(message = "Contact / Director name is required")
    @Size(max = 120, message = "Contact name cannot exceed 120 characters")
    private String contactName;

    @NotBlank(message = "WhatsApp number is required for status updates")
    @Size(max = 20, message = "WhatsApp number cannot exceed 20 characters")
    private String whatsappNumber;

    @NotBlank(message = "Email address is required")
    @Email(message = "Please provide a valid email address")
    @Size(max = 150, message = "Email cannot exceed 150 characters")
    private String email;

    @NotBlank(message = "Feature category is required")
    @Size(max = 50, message = "Feature category cannot exceed 50 characters")
    private String featureCategory;

    @Size(max = 50, message = "Timeline cannot exceed 50 characters")
    private String timeline;

    @NotBlank(message = "Description of the desired feature is required")
    @Size(max = 4000, message = "Description cannot exceed 4000 characters")
    private String description;

    @Builder.Default
    private Boolean requestMeeting = true;
}
