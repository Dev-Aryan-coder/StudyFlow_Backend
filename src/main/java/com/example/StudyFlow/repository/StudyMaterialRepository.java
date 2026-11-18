package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.StudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, String> {

    List<StudyMaterial> findByProjectIdOrderByCreatedAtDesc(String projectId);

    List<StudyMaterial> findByBatchIdOrderByCreatedAtDesc(String batchId);

    List<StudyMaterial> findByProjectIdAndPublicLeadMagnetTrue(String projectId);
}
