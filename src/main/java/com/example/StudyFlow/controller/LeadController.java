package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.entity.StudentLead;
import com.example.StudyFlow.enums.LeadStatus;
import com.example.StudyFlow.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    /**
     * Dashboard: Get all leads captured for an institute
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<StudentLead>>> getLeadsByProject(@PathVariable String projectId) {
        List<StudentLead> leads = leadService.getLeadsByProjectId(projectId);
        return ResponseEntity.ok(ApiResponse.success(leads, "Admission leads retrieved successfully"));
    }

    /**
     * Pipeline: Update lead status (e.g. NEW -> CONTACTED -> CONVERTED)
     */
    @PutMapping("/{leadId}/status")
    public ResponseEntity<ApiResponse<StudentLead>> updateLeadStatus(
            @PathVariable String leadId,
            @RequestParam LeadStatus status) {
        StudentLead updated = leadService.updateLeadStatus(leadId, status);
        return ResponseEntity.ok(ApiResponse.success(updated, "Lead status updated to " + status));
    }
}
