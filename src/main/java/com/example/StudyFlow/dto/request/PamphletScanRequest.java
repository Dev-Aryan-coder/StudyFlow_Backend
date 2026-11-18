package com.example.StudyFlow.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PamphletScanRequest {

    @NotBlank(message = "Base64 image content is required")
    private String base64Image;

    private String additionalNotes;
}

