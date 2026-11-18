package com.example.StudyFlow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectCreateRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Project Name is required")
    @Size(min = 2, max = 150)
    private String projectName;

    @NotBlank(message = "Website Name is required")
    @Size(min = 2, max = 150)
    private String websiteName;

    private String logoUrl; // optional

    @NotBlank(message = "Address is required")
    private String address;

    private String themeColor; // optional, defaults to #2563EB
}
