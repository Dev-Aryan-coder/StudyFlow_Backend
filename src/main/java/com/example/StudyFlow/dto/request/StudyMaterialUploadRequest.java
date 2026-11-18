package com.example.StudyFlow.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudyMaterialUploadRequest {

    @NotBlank(message = "Project ID is required")
    private String projectId;

    private String batchId;

    @NotBlank(message = "Title is required")
    private String title;

    private String chapterName;

    @NotBlank(message = "File URL is required")
    private String fileUrl;

    private String fileType = "PDF";
    private boolean publicLeadMagnet = false;
}
