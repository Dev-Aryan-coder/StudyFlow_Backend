package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.dto.request.ContactInquiryRequest;
import com.example.StudyFlow.dto.request.CustomFeatureRequestDto;
import com.example.StudyFlow.entity.ContactInquiry;
import com.example.StudyFlow.entity.CustomFeatureRequest;
import com.example.StudyFlow.enums.FeatureRequestStatus;
import com.example.StudyFlow.enums.InquiryStatus;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.ContactInquiryRepository;
import com.example.StudyFlow.repository.CustomFeatureRequestRepository;
import com.example.StudyFlow.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactInquiryRepository inquiryRepository;
    private final CustomFeatureRequestRepository featureRequestRepository;

    @Override
    @Transactional
    public ContactInquiry createInquiry(ContactInquiryRequest request) {
        log.info("Received new contact inquiry from: {} ({}) for institute: {}",
                request.getFullName(), request.getEmail(), request.getInstituteName());

        ContactInquiry inquiry = ContactInquiry.builder()
                .fullName(request.getFullName().trim())
                .instituteName(request.getInstituteName() != null ? request.getInstituteName().trim() : null)
                .email(request.getEmail().trim().toLowerCase())
                .phone(request.getPhone().trim())
                .subject(request.getSubject().trim())
                .message(request.getMessage().trim())
                .status(InquiryStatus.UNREAD)
                .build();

        ContactInquiry saved = inquiryRepository.save(inquiry);
        log.info("Successfully persisted contact inquiry ID: {}", saved.getId());
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactInquiry> getAllInquiries() {
        return inquiryRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public ContactInquiry getInquiryById(Long id) {
        return inquiryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact inquiry not found with ID: " + id));
    }

    @Override
    @Transactional
    public ContactInquiry updateInquiryStatus(Long id, InquiryStatus status) {
        ContactInquiry inquiry = getInquiryById(id);
        inquiry.setStatus(status);
        log.info("Updated status for contact inquiry ID: {} to {}", id, status);
        return inquiryRepository.save(inquiry);
    }

    @Override
    @Transactional
    public CustomFeatureRequest createCustomFeatureRequest(CustomFeatureRequestDto request) {
        log.info("Received bespoke custom feature request from: {} for institute: {} [Category: {}, Timeline: {}]",
                request.getContactName(), request.getInstituteName(), request.getFeatureCategory(), request.getTimeline());

        CustomFeatureRequest featureRequest = CustomFeatureRequest.builder()
                .instituteName(request.getInstituteName().trim())
                .contactName(request.getContactName().trim())
                .whatsappNumber(request.getWhatsappNumber().trim())
                .email(request.getEmail().trim().toLowerCase())
                .featureCategory(request.getFeatureCategory().trim())
                .timeline(request.getTimeline() != null ? request.getTimeline().trim() : "standard")
                .description(request.getDescription().trim())
                .requestMeeting(request.getRequestMeeting() != null ? request.getRequestMeeting() : true)
                .status(FeatureRequestStatus.PENDING)
                .build();

        CustomFeatureRequest saved = featureRequestRepository.save(featureRequest);
        log.info("Successfully persisted custom feature request ID: {} for institute: {}",
                saved.getId(), saved.getInstituteName());
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomFeatureRequest> getAllCustomFeatureRequests() {
        return featureRequestRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomFeatureRequest getCustomFeatureRequestById(Long id) {
        return featureRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Custom feature request not found with ID: " + id));
    }

    @Override
    @Transactional
    public CustomFeatureRequest updateFeatureRequestStatus(Long id, FeatureRequestStatus status) {
        CustomFeatureRequest request = getCustomFeatureRequestById(id);
        request.setStatus(status);
        log.info("Updated status for custom feature request ID: {} to {}", id, status);
        return featureRequestRepository.save(request);
    }
}
