package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.ContactInquiryRequest;
import com.example.StudyFlow.dto.request.CustomFeatureRequestDto;
import com.example.StudyFlow.dto.request.UpdateFeatureStatusRequest;
import com.example.StudyFlow.dto.request.UpdateInquiryStatusRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.entity.ContactInquiry;
import com.example.StudyFlow.entity.CustomFeatureRequest;
import com.example.StudyFlow.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContactController {

    private final ContactService contactService;

    /**
     * Submit general inquiry from Contact Us page
     * POST /api/contact/inquiry
     */
    @PostMapping("/inquiry")
    public ResponseEntity<ApiResponse<ContactInquiry>> submitInquiry(
            @Valid @RequestBody ContactInquiryRequest request) {
        log.info("Received public contact inquiry submission: {}", request.getEmail());
        ContactInquiry inquiry = contactService.createInquiry(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(inquiry, "Inquiry submitted successfully. Our team will contact you shortly."));
    }

    /**
     * Submit bespoke custom feature request for a coaching academy
     * POST /api/contact/custom-feature
     */
    @PostMapping("/custom-feature")
    public ResponseEntity<ApiResponse<CustomFeatureRequest>> submitCustomFeature(
            @Valid @RequestBody CustomFeatureRequestDto request) {
        log.info("Received custom feature request for institute: {}", request.getInstituteName());
        CustomFeatureRequest featureRequest = contactService.createCustomFeatureRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(featureRequest, "Custom feature request received. Our engineering team will reach out on WhatsApp within 2 hours."));
    }

    /**
     * Fetch all contact inquiries (admin/management)
     * GET /api/contact/inquiries
     */
    @GetMapping("/inquiries")
    public ResponseEntity<ApiResponse<List<ContactInquiry>>> getAllInquiries() {
        List<ContactInquiry> list = contactService.getAllInquiries();
        return ResponseEntity.ok(ApiResponse.success(list, "Fetched " + list.size() + " inquiries."));
    }

    /**
     * Fetch single inquiry by ID
     * GET /api/contact/inquiries/{id}
     */
    @GetMapping("/inquiries/{id}")
    public ResponseEntity<ApiResponse<ContactInquiry>> getInquiryById(@PathVariable Long id) {
        ContactInquiry inquiry = contactService.getInquiryById(id);
        return ResponseEntity.ok(ApiResponse.success(inquiry, "Inquiry details retrieved."));
    }

    /**
     * Update inquiry processing status
     * PATCH /api/contact/inquiries/{id}/status
     */
    @PatchMapping("/inquiries/{id}/status")
    public ResponseEntity<ApiResponse<ContactInquiry>> updateInquiryStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInquiryStatusRequest request) {
        ContactInquiry updated = contactService.updateInquiryStatus(id, request.getStatus());
        return ResponseEntity.ok(ApiResponse.success(updated, "Inquiry status updated to " + request.getStatus()));
    }

    /**
     * Fetch all custom feature requests (admin/management)
     * GET /api/contact/custom-features
     */
    @GetMapping("/custom-features")
    public ResponseEntity<ApiResponse<List<CustomFeatureRequest>>> getAllCustomFeatures() {
        List<CustomFeatureRequest> list = contactService.getAllCustomFeatureRequests();
        return ResponseEntity.ok(ApiResponse.success(list, "Fetched " + list.size() + " custom feature requests."));
    }

    /**
     * Fetch single custom feature request by ID
     * GET /api/contact/custom-features/{id}
     */
    @GetMapping("/custom-features/{id}")
    public ResponseEntity<ApiResponse<CustomFeatureRequest>> getCustomFeatureById(@PathVariable Long id) {
        CustomFeatureRequest request = contactService.getCustomFeatureRequestById(id);
        return ResponseEntity.ok(ApiResponse.success(request, "Custom feature details retrieved."));
    }

    /**
     * Update custom feature request status
     * PATCH /api/contact/custom-features/{id}/status
     */
    @PatchMapping("/custom-features/{id}/status")
    public ResponseEntity<ApiResponse<CustomFeatureRequest>> updateFeatureStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFeatureStatusRequest request) {
        CustomFeatureRequest updated = contactService.updateFeatureRequestStatus(id, request.getStatus());
        return ResponseEntity.ok(ApiResponse.success(updated, "Feature request status updated to " + request.getStatus()));
    }
}
