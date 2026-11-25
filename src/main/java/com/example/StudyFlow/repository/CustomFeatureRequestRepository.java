package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.CustomFeatureRequest;
import com.example.StudyFlow.enums.FeatureRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomFeatureRequestRepository extends JpaRepository<CustomFeatureRequest, Long> {

    List<CustomFeatureRequest> findAllByOrderByCreatedAtDesc();

    List<CustomFeatureRequest> findByStatusOrderByCreatedAtDesc(FeatureRequestStatus status);

    List<CustomFeatureRequest> findByFeatureCategoryOrderByCreatedAtDesc(String featureCategory);

    List<CustomFeatureRequest> findByWhatsappNumberOrderByCreatedAtDesc(String whatsappNumber);

    long countByStatus(FeatureRequestStatus status);
}
