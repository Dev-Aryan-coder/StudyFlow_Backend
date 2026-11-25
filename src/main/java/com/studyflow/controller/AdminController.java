package com.studyflow.controller;

import com.studyflow.dto.response.ApiResponse;
import com.studyflow.entity.InstituteConfig;
import com.studyflow.entity.StudentLead;
import com.studyflow.service.InstituteService;
import com.studyflow.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LeadService leadService;
    private final InstituteService instituteService;

    @GetMapping("/leads/{instituteId}")
    public ResponseEntity<ApiResponse<List<StudentLead>>> getLeads(@PathVariable String instituteId) {
        List<StudentLead> leads = leadService.getLeadsByInstitute(instituteId);
        return ResponseEntity.ok(ApiResponse.success(leads, "Leads retrieved successfully"));
    }

    @PutMapping("/config/{instituteId}")
    public ResponseEntity<ApiResponse<InstituteConfig>> updateLayoutConfig(
            @PathVariable String instituteId,
            @RequestBody Map<String, String> request) {
        String layoutData = request.get("layoutData");
        InstituteConfig updated = instituteService.updateConfig(instituteId, layoutData);
        return ResponseEntity.ok(ApiResponse.success(updated, "Layout configuration updated successfully"));
    }
}
