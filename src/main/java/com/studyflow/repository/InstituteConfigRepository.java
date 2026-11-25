package com.studyflow.repository;

import com.studyflow.entity.InstituteConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstituteConfigRepository extends JpaRepository<InstituteConfig, String> {

    Optional<InstituteConfig> findByInstituteId(String instituteId);

    Optional<InstituteConfig> findByInstituteSlug(String slug);
}
