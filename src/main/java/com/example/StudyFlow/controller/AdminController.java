package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.entity.StudentLead;
import com.example.StudyFlow.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LeadService leadService;

    /**
     * Get all admission inquiries / leads for a project
     */
    @GetMapping("/leads/{projectId}")
    public ResponseEntity<ApiResponse<List<StudentLead>>> getProjectLeads(@PathVariable String projectId) {
        List<StudentLead> leads = leadService.getLeadsByProjectId(projectId);
        return ResponseEntity.ok(ApiResponse.success(leads, "Leads retrieved successfully"));
    }
}
