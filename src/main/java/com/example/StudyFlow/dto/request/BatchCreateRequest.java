package com.example.StudyFlow.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BatchCreateRequest {

    @NotBlank(message = "Project ID is required")
    private String projectId;

    @NotBlank(message = "Batch name is required")
    private String name;

    private String subject;

    @NotBlank(message = "Timing slot is required")
    private String timingSlot;

    private Integer maxCapacity = 40;
}
