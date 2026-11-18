package com.example.StudyFlow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private String id;
    private String userId;
    private String projectName;
    private String websiteName;
    private String slug;
    private String logoUrl;
    private String address;
    private String themeColor;
    private boolean active;
    private String layoutData; // Default or configured layout JSON
    private LocalDateTime createdAt;
}
