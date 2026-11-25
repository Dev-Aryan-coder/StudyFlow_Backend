package com.studyflow.repository;

import com.studyflow.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    List<Teacher> findByInstituteIdAndActiveTrue(String instituteId);

    long countByInstituteId(String instituteId);
}
