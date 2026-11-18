package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, String> {

    List<Batch> findByProjectIdAndActiveTrue(String projectId);

    List<Batch> findByProjectSlugAndActiveTrue(String slug);
}
