package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    List<Project> findByUserIdAndActiveTrueOrderByCreatedAtDesc(String userId);

    Optional<Project> findBySlug(String slug);

    boolean existsBySlug(String slug);
}
