package com.studyflow.repository;

import com.studyflow.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, String> {

    Optional<Institute> findBySlug(String slug);

    boolean existsBySlug(String slug);

    boolean existsByEmail(String email);
}
