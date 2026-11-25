package com.example.StudyFlow.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * WebsiteLayout
 * MongoDB Document representation of a complete tenant website layout.
 * Enables zero-downtime schema evolution, dynamic component trees, instant drafts/publishing,
 * and 1-click revision rollbacks.
 */
@Document(collection = "website_layouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebsiteLayout {

    @Id
    private String id;

    @Indexed(unique = true)
    private String projectId;

    @Indexed(unique = true)
    private String slug;

    private String websiteName;
    private String themeColor;

    /**
     * Stored as raw JSON text or structured document for instant frontend canvas consumption
     */
    private String draftLayout;
    private String publishedLayout;

    @Builder.Default
    private int version = 1;

    @Builder.Default
    private boolean published = true;

    @Builder.Default
    private List<LayoutRevision> history = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LayoutRevision {
        private int version;
        private String layoutSnapshot;
        private LocalDateTime savedAt;
    }
}
