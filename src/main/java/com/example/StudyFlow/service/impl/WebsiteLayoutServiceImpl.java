package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.document.WebsiteLayout;
import com.example.StudyFlow.document.WebsiteLayout.LayoutRevision;
import com.example.StudyFlow.entity.Project;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.ProjectRepository;
import com.example.StudyFlow.repository.mongo.WebsiteLayoutRepository;
import com.example.StudyFlow.service.WebsiteLayoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebsiteLayoutServiceImpl implements WebsiteLayoutService {

    private final WebsiteLayoutRepository layoutRepository;
    private final ProjectRepository projectRepository;

    @Override
    public WebsiteLayout initializeLayout(String projectId, String slug, String websiteName, String themeColor, String initialLayoutJson) {
        WebsiteLayout layout = WebsiteLayout.builder()
                .projectId(projectId)
                .slug(slug)
                .websiteName(websiteName)
                .themeColor(themeColor)
                .draftLayout(initialLayoutJson)
                .publishedLayout(initialLayoutJson)
                .version(1)
                .published(true)
                .history(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(LocalDateTime.now())
                .build();

        WebsiteLayout saved = layoutRepository.save(layout);
        log.info("Initialized website layout in MongoDB for project: {} (slug: {})", projectId, slug);
        return saved;
    }

    @Override
    public WebsiteLayout getDraftLayout(String projectId) {
        return layoutRepository.findByProjectId(projectId)
                .orElseGet(() -> {
                    Project project = projectRepository.findById(projectId)
                            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
                    return initializeLayout(projectId, project.getSlug(), project.getWebsiteName(), project.getThemeColor(), "{}");
                });
    }

    @Override
    public WebsiteLayout updateDraftLayout(String projectId, String layoutData) {
        WebsiteLayout layout = getDraftLayout(projectId);
        layout.setDraftLayout(layoutData);
        layout.setUpdatedAt(LocalDateTime.now());
        WebsiteLayout saved = layoutRepository.save(layout);
        log.info("Updated draft layout in MongoDB for project: {}", projectId);
        return saved;
    }

    @Override
    public WebsiteLayout publishLayout(String projectId) {
        WebsiteLayout layout = getDraftLayout(projectId);

        // Safety tripwire: strip preview flags before publishing live
        String draft = layout.getDraftLayout();
        if (draft != null) {
            draft = draft.replaceAll("\"isGlobalSampleActive\"\\s*:\\s*true", "\"isGlobalSampleActive\":false")
                         .replaceAll("\"isSampleActive\"\\s*:\\s*true", "\"isSampleActive\":false");
        }

        // Save current published version to history before updating
        if (layout.getPublishedLayout() != null) {
            if (layout.getHistory() == null) {
                layout.setHistory(new ArrayList<>());
            }
            layout.getHistory().add(LayoutRevision.builder()
                    .version(layout.getVersion())
                    .layoutSnapshot(layout.getPublishedLayout())
                    .savedAt(layout.getPublishedAt() != null ? layout.getPublishedAt() : LocalDateTime.now())
                    .build());
        }

        layout.setPublishedLayout(draft);
        layout.setVersion(layout.getVersion() + 1);
        layout.setPublished(true);
        layout.setPublishedAt(LocalDateTime.now());
        layout.setUpdatedAt(LocalDateTime.now());

        WebsiteLayout saved = layoutRepository.save(layout);
        log.info("Published website layout in MongoDB for project: {} (Version: {})", projectId, saved.getVersion());
        return saved;
    }

    @Override
    public String getPublishedLayoutBySlug(String slug) {
        return layoutRepository.findBySlug(slug)
                .map(WebsiteLayout::getPublishedLayout)
                .orElse("{}");
    }

    @Override
    public WebsiteLayout rollbackToVersion(String projectId, int versionNumber) {
        WebsiteLayout layout = getDraftLayout(projectId);
        if (layout.getHistory() == null || layout.getHistory().isEmpty()) {
            throw new ResourceNotFoundException("No revision history found for project: " + projectId);
        }

        LayoutRevision revision = layout.getHistory().stream()
                .filter(r -> r.getVersion() == versionNumber)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Version " + versionNumber + " not found in history for project: " + projectId));

        layout.setDraftLayout(revision.getLayoutSnapshot());
        layout.setPublishedLayout(revision.getLayoutSnapshot());
        layout.setUpdatedAt(LocalDateTime.now());
        layout.setPublishedAt(LocalDateTime.now());

        WebsiteLayout saved = layoutRepository.save(layout);
        log.info("Rolled back project {} to version {}", projectId, versionNumber);
        return saved;
    }
}
