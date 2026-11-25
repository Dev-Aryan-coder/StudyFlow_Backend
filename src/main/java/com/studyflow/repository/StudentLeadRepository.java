package com.studyflow.repository;

import com.studyflow.entity.StudentLead;
import com.studyflow.enums.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentLeadRepository extends JpaRepository<StudentLead, String> {

    List<StudentLead> findByInstituteIdOrderByCreatedAtDesc(String instituteId);

    List<StudentLead> findByInstituteIdAndStatus(String instituteId, LeadStatus status);

    long countByInstituteId(String instituteId);
}
