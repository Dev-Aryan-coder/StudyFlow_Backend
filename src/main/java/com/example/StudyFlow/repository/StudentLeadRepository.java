package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.StudentLead;
import com.example.StudyFlow.enums.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentLeadRepository extends JpaRepository<StudentLead, String> {

    List<StudentLead> findByProjectIdOrderByCreatedAtDesc(String projectId);

    List<StudentLead> findByProjectIdAndStatus(String projectId, LeadStatus status);

    long countByProjectId(String projectId);
}
