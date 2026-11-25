package com.studyflow.repository;

import com.studyflow.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, String> {

    List<Batch> findByInstituteIdAndActiveTrue(String instituteId);

    List<Batch> findByInstituteSlugAndActiveTrue(String slug);
}
