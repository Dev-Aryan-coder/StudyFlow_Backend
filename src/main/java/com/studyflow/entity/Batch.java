package com.studyflow.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "batches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id", nullable = false)
    private Institute institute;

    @Column(nullable = false, length = 100)
    private String name; // e.g. "Class 11 Target JEE"

    @Column(length = 100)
    private String subject; // e.g. "Physics"

    @Column(name = "timing_slot", nullable = false, length = 100)
    private String timingSlot; // e.g. "Mon-Wed-Fri, 4:00 PM - 6:00 PM"

    @Column(name = "max_capacity")
    @Builder.Default
    private Integer maxCapacity = 40;

    @Column(name = "current_enrollment")
    @Builder.Default
    private Integer currentEnrollment = 0;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
