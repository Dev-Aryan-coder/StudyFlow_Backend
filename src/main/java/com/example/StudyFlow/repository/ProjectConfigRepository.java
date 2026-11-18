package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.ProjectConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectConfigRepository extends JpaRepository<ProjectConfig, String> {

    Optional<ProjectConfig> findByProjectId(String projectId);

    Optional<ProjectConfig> findByProjectSlug(String slug);
}
