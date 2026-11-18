package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findByProjectIdAndActiveTrue(String projectId);

    List<Student> findByBatchIdAndActiveTrue(String batchId);

    long countByProjectId(String projectId);

    @Query("SELECT s FROM Student s WHERE s.project.id = :projectId AND (s.rollNumber = :identifier OR s.email = :identifier OR s.phoneNumber = :identifier)")
    Optional<Student> findByProjectAndIdentifier(@Param("projectId") String projectId, @Param("identifier") String identifier);
}
