package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.dto.request.ProjectCreateRequest;
import com.example.StudyFlow.dto.response.ProjectResponse;
import com.example.StudyFlow.entity.Project;
import com.example.StudyFlow.entity.ProjectConfig;
import com.example.StudyFlow.entity.User;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.ProjectConfigRepository;
import com.example.StudyFlow.repository.ProjectRepository;
import com.example.StudyFlow.repository.UserRepository;
import com.example.StudyFlow.service.ProjectService;
import com.example.StudyFlow.util.SlugUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectConfigRepository configRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        // Generate clean URL slug from website name
        String baseSlug = SlugUtils.toSlug(request.getWebsiteName());
        String slug = baseSlug;
        int counter = 1;
        while (projectRepository.existsBySlug(slug)) {
            slug = baseSlug + "-" + counter++;
        }

        Project project = Project.builder()
                .user(user)
                .projectName(request.getProjectName().trim())
                .websiteName(request.getWebsiteName().trim())
                .slug(slug)
                .logoUrl(request.getLogoUrl())
                .address(request.getAddress().trim())
                .themeColor(request.getThemeColor() != null ? request.getThemeColor() : "#2563EB")
                .active(true)
                .build();

        Project savedProject = projectRepository.save(project);

        // Initialize 3-Tier Site Structure with 5 Welcoming Pages and Auth Configuration
        String defaultLayoutJson = """
            {
              "theme": {
                "primaryColor": "%s",
                "fontFamily": "Inter",
                "darkMode": false,
                "borderRadius": "8px"
              },
              "structure": {
                "welcomingPages": ["home", "about", "testimonials", "services", "contact"],
                "authEnabled": true,
                "sideNavbarEnabled": true
              },
              "authSettings": {
                "loginStyle": "SPLIT_SCREEN",
                "identifierType": "STUDENT_ID",
                "showForgotPassword": true,
                "placeholderText": "Enter Roll Number, Email, or Mobile"
              },
              "pages": {
                "home": {
                  "hero": {
                    "enabled": true,
                    "title": "Welcome to %s",
                    "subtitle": "Top Mentorship & Structured Courses for Board & Competitive Excellence.",
                    "teacherName": "%s",
                    "badge": "Admissions Open 2026-27",
                    "ctaButton": "Book Free Demo"
                  },
                  "batches": {
                    "enabled": true,
                    "items": [
                      {"name": "Class 10 CBSE Foundation", "timing": "Mon-Fri, 4:30 PM - 7:00 PM", "seats": 8, "fee": "₹2,500/mo"},
                      {"name": "JEE Advanced Target Batch", "timing": "Mon, Wed, Fri, 5:00 PM - 8:30 PM", "seats": 4, "fee": "₹4,800/mo"}
                    ]
                  },
                  "contact": {
                    "enabled": true,
                    "address": "%s",
                    "phoneNumber": "%s"
                  }
                },
                "about": {
                  "title": "About Our Academy",
                  "tagline": "Empowering students through concept clarity and personalized attention.",
                  "mentor": "%s",
                  "experienceYears": "12+ Years",
                  "totalSelections": "500+"
                },
                "testimonials": {
                  "title": "What Parents & Students Say",
                  "items": [
                    {"author": "Rohan Sharma (Class 10)", "score": "98.4%%", "comment": "The test series and daily doubt solving helped me score 98%% in boards!"}
                  ]
                },
                "services": {
                  "title": "Our Batches & Programs",
                  "programs": ["CBSE Board Preparation", "State Board Intensive", "Crash Courses & Test Series"]
                },
                "contact": {
                  "title": "Visit or Inquire",
                  "address": "%s",
                  "phone": "%s"
                }
              }
            }
            """.formatted(
                savedProject.getThemeColor(),
                savedProject.getWebsiteName(),
                user.getUsername(),
                savedProject.getAddress().replace("\n", " "),
                user.getPhoneNumber(),
                user.getUsername(),
                savedProject.getAddress().replace("\n", " "),
                user.getPhoneNumber()
            );

        ProjectConfig config = ProjectConfig.builder()
                .project(savedProject)
                .layoutData(defaultLayoutJson)
                .build();

        configRepository.save(config);
        log.info("Project & website initialized with 3-Tier Site Structure: {} (Slug: {})", savedProject.getProjectName(), savedProject.getSlug());

        return mapToProjectResponse(savedProject, defaultLayoutJson);
    }

    @Override
    public List<ProjectResponse> getProjectsByUserId(String userId) {
        return projectRepository.findByUserIdAndActiveTrueOrderByCreatedAtDesc(userId)
                .stream()
                .map(p -> {
                    String layout = configRepository.findByProjectId(p.getId())
                            .map(ProjectConfig::getLayoutData)
                            .orElse("{}");
                    return mapToProjectResponse(p, layout);
                })
                .toList();
    }

    @Override
    public ProjectResponse getProjectById(String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        String layout = configRepository.findByProjectId(projectId)
                .map(ProjectConfig::getLayoutData)
                .orElse("{}");

        return mapToProjectResponse(project, layout);
    }

    @Override
    public Project getBySlug(String slug) {
        return projectRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("No website found with link: " + slug));
    }

    @Override
    public ProjectConfig getConfigBySlug(String slug) {
        return configRepository.findByProjectSlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Website configuration not found for: " + slug));
    }

    @Override
    @Transactional
    public ProjectConfig updateLayoutConfig(String projectId, String layoutData) {
        ProjectConfig config = configRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project config not found for project id: " + projectId));

        config.setLayoutData(layoutData);
        return configRepository.save(config);
    }

    @Override
    @Transactional
    public Map<String, Object> publishWebsite(String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        ProjectConfig config = configRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Config not found for project: " + projectId));

        // Publish Safety Tripwire: strip preview flags before publishing live
        String layout = config.getLayoutData();
        if (layout != null) {
            layout = layout.replaceAll("\"isGlobalSampleActive\"\\s*:\\s*true", "\"isGlobalSampleActive\":false")
                           .replaceAll("\"isSampleActive\"\\s*:\\s*true", "\"isSampleActive\":false");
            config.setLayoutData(layout);
            configRepository.save(config);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("projectId", project.getId());
        result.put("status", "PUBLISHED");
        result.put("slug", project.getSlug());
        result.put("liveUrl", "https://" + project.getSlug() + ".studyflow.in");
        result.put("publishedAt", LocalDateTime.now().toString());

        log.info("Project {} published live successfully to slug: {}", projectId, project.getSlug());
        return result;
    }

    private ProjectResponse mapToProjectResponse(Project project, String layoutData) {
        return ProjectResponse.builder()
                .id(project.getId())
                .userId(project.getUser().getId())
                .projectName(project.getProjectName())
                .websiteName(project.getWebsiteName())
                .slug(project.getSlug())
                .logoUrl(project.getLogoUrl())
                .address(project.getAddress())
                .themeColor(project.getThemeColor())
                .active(project.isActive())
                .layoutData(layoutData)
                .createdAt(project.getCreatedAt())
                .build();
    }
}
