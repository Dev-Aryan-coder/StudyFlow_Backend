package com.studyflow.controller;

import com.studyflow.dto.request.StudentLeadRequest;
import com.studyflow.dto.response.ApiResponse;
import com.studyflow.entity.Institute;
import com.studyflow.entity.InstituteConfig;
import com.studyflow.entity.StudentLead;
import com.studyflow.service.InstituteService;
import com.studyflow.service.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {

    private final InstituteService instituteService;
    private final LeadService leadService;

    /**
     * Resolves public institute website layout and metadata by slug
     * Example: GET /api/public/apex-science
     */
    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPublicSite(@PathVariable String slug) {
        Institute institute = instituteService.getBySlug(slug);
        InstituteConfig config = instituteService.getConfigBySlug(slug);

        Map<String, Object> response = Map.of(
            "id", institute.getId(),
            "name", institute.getName(),
            "slug", institute.getSlug(),
            "themeColor", institute.getThemeColor(),
            "whatsappNumber", institute.getWhatsappNumber(),
            "layout", config.getLayoutData()
        );

        return ResponseEntity.ok(ApiResponse.success(response, "Public site resolved successfully"));
    }

    /**
     * Captures incoming demo class inquiries from public website
     * Example: POST /api/public/lead
     */
    @PostMapping("/lead")
    public ResponseEntity<ApiResponse<StudentLead>> submitLead(@Valid @RequestBody StudentLeadRequest request) {
        StudentLead lead = leadService.createLead(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(lead, "Admission enquiry submitted successfully"));
    }
}
