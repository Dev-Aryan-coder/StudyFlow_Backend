package com.example.StudyFlow.controller;

import com.example.StudyFlow.document.WebsiteLayout;
import com.example.StudyFlow.dto.request.LayoutConfigUpdateRequest;
import com.example.StudyFlow.dto.request.ProjectCreateRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.dto.response.ProjectResponse;
import com.example.StudyFlow.service.ProjectService;
import com.example.StudyFlow.service.WebsiteLayoutService;
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
    private final WebsiteLayoutService websiteLayoutService;

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
     * Editor Mode - Get Draft Layout from MongoDB
     */
    @GetMapping("/{projectId}/layout/draft")
    public ResponseEntity<ApiResponse<WebsiteLayout>> getDraftLayout(@PathVariable String projectId) {
        WebsiteLayout layout = websiteLayoutService.getDraftLayout(projectId);
        return ResponseEntity.ok(ApiResponse.success(layout, "Draft layout retrieved from MongoDB"));
    }

    /**
     * Editor Mode - Save / Update Draft Layout in MongoDB
     */
    @PutMapping("/{projectId}/layout/draft")
    public ResponseEntity<ApiResponse<WebsiteLayout>> updateDraftLayout(
            @PathVariable String projectId,
            @Valid @RequestBody LayoutConfigUpdateRequest request) {
        WebsiteLayout updated = websiteLayoutService.updateDraftLayout(projectId, request.getLayoutData());
        return ResponseEntity.ok(ApiResponse.success(updated, "Draft layout autosaved to MongoDB"));
    }

    /**
     * Publish website layout in MongoDB with Safety Tripwire & version increment
     */
    @PostMapping("/{projectId}/layout/publish")
    public ResponseEntity<ApiResponse<WebsiteLayout>> publishLayout(@PathVariable String projectId) {
        WebsiteLayout published = websiteLayoutService.publishLayout(projectId);
        return ResponseEntity.ok(ApiResponse.success(published, "Website published live! Tripwire auto-disabled preview mode."));
    }

    /**
     * Rollback website layout to a previous version in MongoDB
     */
    @PostMapping("/{projectId}/layout/rollback/{version}")
    public ResponseEntity<ApiResponse<WebsiteLayout>> rollbackLayout(
            @PathVariable String projectId,
            @PathVariable int version) {
        WebsiteLayout rolledBack = websiteLayoutService.rollbackToVersion(projectId, version);
        return ResponseEntity.ok(ApiResponse.success(rolledBack, "Successfully rolled back layout to version " + version));
    }

    /**
     * Backward-compatible legacy update endpoint
     */
    @PutMapping("/{projectId}/config")
    public ResponseEntity<ApiResponse<WebsiteLayout>> updateLayoutConfigLegacy(
            @PathVariable String projectId,
            @Valid @RequestBody LayoutConfigUpdateRequest request) {
        WebsiteLayout updated = websiteLayoutService.updateDraftLayout(projectId, request.getLayoutData());
        websiteLayoutService.publishLayout(projectId);
        return ResponseEntity.ok(ApiResponse.success(updated, "Website layout updated and published live!"));
    }

    /**
     * Backward-compatible legacy publish endpoint
     */
    @PostMapping("/{projectId}/publish")
    public ResponseEntity<ApiResponse<Map<String, Object>>> publishWebsiteLegacy(@PathVariable String projectId) {
        Map<String, Object> publishResult = projectService.publishWebsite(projectId);
        return ResponseEntity.ok(ApiResponse.success(publishResult, "Website published live! Tripwire auto-disabled preview mode."));
    }
}
