package com.example.StudyFlow.entity;

import com.example.StudyFlow.enums.FeatureRequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "custom_feature_requests", indexes = {
    @Index(name = "idx_cfr_institute", columnList = "institute_name"),
    @Index(name = "idx_cfr_status", columnList = "status"),
    @Index(name = "idx_cfr_created_at", columnList = "created_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomFeatureRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "institute_name", nullable = false, length = 150)
    private String instituteName;

    @Column(name = "contact_name", nullable = false, length = 120)
    private String contactName;

    @Column(name = "whatsapp_number", nullable = false, length = 20)
    private String whatsappNumber;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(name = "feature_category", nullable = false, length = 50)
    private String featureCategory;

    @Column(length = 50)
    private String timeline;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "request_meeting", nullable = false)
    @Builder.Default
    private Boolean requestMeeting = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    @Builder.Default
    private FeatureRequestStatus status = FeatureRequestStatus.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
