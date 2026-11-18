package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    List<Teacher> findByProjectIdAndActiveTrue(String projectId);

    long countByProjectId(String projectId);
}
