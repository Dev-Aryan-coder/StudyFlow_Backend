package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.dto.request.StudentLeadRequest;
import com.example.StudyFlow.entity.Project;
import com.example.StudyFlow.entity.StudentLead;
import com.example.StudyFlow.enums.LeadStatus;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.ProjectRepository;
import com.example.StudyFlow.repository.StudentLeadRepository;
import com.example.StudyFlow.service.EmailService;
import com.example.StudyFlow.service.LeadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {

    private final StudentLeadRepository leadRepository;
    private final ProjectRepository projectRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public StudentLead createLead(StudentLeadRequest request) {
        Project project = projectRepository.findBySlug(request.getInstituteSlug())
                .orElseThrow(() -> new ResourceNotFoundException("Institute website not found: " + request.getInstituteSlug()));

        StudentLead lead = StudentLead.builder()
                .project(project)
                .studentName(request.getStudentName().trim())
                .parentPhone(request.getParentPhone().trim())
                .targetClass(request.getTargetClass())
                .message(request.getMessage())
                .status(LeadStatus.NEW)
                .build();

        StudentLead savedLead = leadRepository.save(lead);

        // Async email notification to the tutor
        if (project.getUser() != null && project.getUser().getEmail() != null) {
            emailService.sendAdmissionConfirmationEmail(
                    project.getUser().getEmail(),
                    savedLead.getStudentName(),
                    project.getWebsiteName(),
                    savedLead.getTargetClass() != null ? savedLead.getTargetClass() : "Demo Class"
            );
        }

        return savedLead;
    }

    @Override
    public List<StudentLead> getLeadsByProjectId(String projectId) {
        return leadRepository.findByProjectIdOrderByCreatedAtDesc(projectId);
    }

    @Override
    @Transactional
    public StudentLead updateLeadStatus(String leadId, LeadStatus status) {
        StudentLead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with id: " + leadId));

        lead.setStatus(status);
        log.info("Lead {} status updated to: {}", leadId, status);
        return leadRepository.save(lead);
    }
}
