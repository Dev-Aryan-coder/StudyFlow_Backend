package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.LayoutConfigUpdateRequest;
import com.example.StudyFlow.dto.request.ProjectCreateRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.dto.response.ProjectResponse;
import com.example.StudyFlow.entity.ProjectConfig;
import com.example.StudyFlow.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * 4-Step Project / Website Creation Wizard - Final Confirmation
     * Input: userId, projectName (Step 1), websiteName (Step 2), logoUrl (Step 3), address (Step 4)
     * Creates project in MySQL, auto-generates slug, seeds default layout, and returns ready for Editor Mode!
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProjectResponse>> createProject(@Valid @RequestBody ProjectCreateRequest request) {
        ProjectResponse response = projectService.createProject(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Website created successfully! Transitioning to editor mode."));
    }

    /**
     * Dashboard - Get all projects owned by the user (shown below greeting)
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getUserProjects(@PathVariable String userId) {
        List<ProjectResponse> projects = projectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success(projects, "Projects retrieved successfully"));
    }

    /**
     * Get single project details
     */
    @GetMapping("/{projectId}")
    public ResponseEntity<ApiResponse<ProjectResponse>> getProjectById(@PathVariable String projectId) {
        ProjectResponse response = projectService.getProjectById(projectId);
        return ResponseEntity.ok(ApiResponse.success(response, "Project details retrieved successfully"));
    }

    /**
     * Editor Mode - Save / Update modular website layout configuration JSON
     */
    @PutMapping("/{projectId}/config")
    public ResponseEntity<ApiResponse<ProjectConfig>> updateLayoutConfig(
            @PathVariable String projectId,
            @Valid @RequestBody LayoutConfigUpdateRequest request) {
        ProjectConfig updated = projectService.updateLayoutConfig(projectId, request.getLayoutData());
        return ResponseEntity.ok(ApiResponse.success(updated, "Website layout updated and published live!"));
    }

    /**
     * Publish Safety Tripwire: Sanitizes preview flags and pushes website live to public subdomain
     * Example: POST /api/projects/{projectId}/publish
     */
    @PostMapping("/{projectId}/publish")
    public ResponseEntity<ApiResponse<Map<String, Object>>> publishWebsite(@PathVariable String projectId) {
        Map<String, Object> publishResult = projectService.publishWebsite(projectId);
        return ResponseEntity.ok(ApiResponse.success(publishResult, "Website published live! Tripwire auto-disabled preview mode."));
    }
}
