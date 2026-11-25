package com.studyflow.service;

import com.studyflow.dto.request.StudentLeadRequest;
import com.studyflow.entity.StudentLead;

import java.util.List;

public interface LeadService {

    StudentLead createLead(StudentLeadRequest request);

    List<StudentLead> getLeadsByInstitute(String instituteId);
}
