package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.StudyMaterialUploadRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.entity.StudyMaterial;
import com.example.StudyFlow.service.StudyMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class StudyMaterialController {

    private final StudyMaterialService materialService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<StudyMaterial>> uploadMaterial(@Valid @RequestBody StudyMaterialUploadRequest request) {
        StudyMaterial material = materialService.uploadMaterial(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(material, "Study material published successfully"));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<StudyMaterial>>> getMaterialsByProject(@PathVariable String projectId) {
        List<StudyMaterial> list = materialService.getMaterialsByProjectId(projectId);
        return ResponseEntity.ok(ApiResponse.success(list, "Study materials retrieved successfully"));
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<ApiResponse<List<StudyMaterial>>> getMaterialsByBatch(@PathVariable String batchId) {
        List<StudyMaterial> list = materialService.getMaterialsByBatchId(batchId);
        return ResponseEntity.ok(ApiResponse.success(list, "Batch study materials retrieved"));
    }
}
