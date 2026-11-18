package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.StudentLeadRequest;
import com.example.StudyFlow.entity.StudentLead;
import com.example.StudyFlow.enums.LeadStatus;

import java.util.List;

public interface LeadService {

    StudentLead createLead(StudentLeadRequest request);

    List<StudentLead> getLeadsByProjectId(String projectId);

    StudentLead updateLeadStatus(String leadId, LeadStatus status);
}
