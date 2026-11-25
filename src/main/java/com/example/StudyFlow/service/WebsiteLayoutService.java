package com.example.StudyFlow.service;

import com.example.StudyFlow.document.WebsiteLayout;

public interface WebsiteLayoutService {

    WebsiteLayout initializeLayout(String projectId, String slug, String websiteName, String themeColor, String initialLayoutJson);

    WebsiteLayout getDraftLayout(String projectId);

    WebsiteLayout updateDraftLayout(String projectId, String layoutData);

    WebsiteLayout publishLayout(String projectId);

    String getPublishedLayoutBySlug(String slug);

    WebsiteLayout rollbackToVersion(String projectId, int versionNumber);
}
