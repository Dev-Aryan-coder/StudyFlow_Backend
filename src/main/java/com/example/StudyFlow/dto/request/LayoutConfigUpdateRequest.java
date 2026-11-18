package com.example.StudyFlow.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LayoutConfigUpdateRequest {

    @NotBlank(message = "Layout data JSON is required")
    private String layoutData;
}
