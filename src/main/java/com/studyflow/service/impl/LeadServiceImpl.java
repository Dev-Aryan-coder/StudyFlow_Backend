package com.studyflow.service.impl;

import com.studyflow.dto.request.StudentLeadRequest;
import com.studyflow.entity.Institute;
import com.studyflow.entity.StudentLead;
import com.studyflow.enums.LeadStatus;
import com.studyflow.exception.ResourceNotFoundException;
import com.studyflow.repository.InstituteRepository;
import com.studyflow.repository.StudentLeadRepository;
import com.studyflow.service.EmailService;
import com.studyflow.service.LeadService;
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
    private final InstituteRepository instituteRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public StudentLead createLead(StudentLeadRequest request) {
        Institute institute = instituteRepository.findBySlug(request.getInstituteSlug())
                .orElseThrow(() -> new ResourceNotFoundException("Institute not found with slug: " + request.getInstituteSlug()));

        StudentLead lead = StudentLead.builder()
                .institute(institute)
                .studentName(request.getStudentName())
                .parentPhone(request.getParentPhone())
                .targetClass(request.getTargetClass())
                .message(request.getMessage())
                .status(LeadStatus.NEW)
                .build();

        StudentLead savedLead = leadRepository.save(lead);

        // Async email confirmation
        if (institute.getEmail() != null) {
            emailService.sendAdmissionConfirmationEmail(
                    institute.getEmail(),
                    savedLead.getStudentName(),
                    institute.getName(),
                    savedLead.getTargetClass()
            );
        }

        return savedLead;
    }

    @Override
    public List<StudentLead> getLeadsByInstitute(String instituteId) {
        return leadRepository.findByInstituteIdOrderByCreatedAtDesc(instituteId);
    }
}
