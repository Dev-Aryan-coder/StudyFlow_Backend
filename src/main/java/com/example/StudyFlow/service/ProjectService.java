package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.ProjectCreateRequest;
import com.example.StudyFlow.dto.response.ProjectResponse;
import com.example.StudyFlow.entity.Project;
import com.example.StudyFlow.entity.ProjectConfig;

import java.util.List;
import java.util.Map;

public interface ProjectService {

    ProjectResponse createProject(ProjectCreateRequest request);

    List<ProjectResponse> getProjectsByUserId(String userId);

    ProjectResponse getProjectById(String projectId);

    Project getBySlug(String slug);

    ProjectConfig getConfigBySlug(String slug);

    String getPublishedLayout(String slug);

    ProjectConfig updateLayoutConfig(String projectId, String layoutData);

    Map<String, Object> publishWebsite(String projectId);
}
