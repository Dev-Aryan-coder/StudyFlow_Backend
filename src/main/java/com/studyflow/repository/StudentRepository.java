package com.studyflow.repository;

import com.studyflow.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findByInstituteIdAndActiveTrue(String instituteId);

    List<Student> findByBatchIdAndActiveTrue(String batchId);

    long countByInstituteId(String instituteId);
}
