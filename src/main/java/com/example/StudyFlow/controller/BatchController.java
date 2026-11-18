package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.BatchCreateRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.entity.Batch;
import com.example.StudyFlow.service.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<ApiResponse<Batch>> createBatch(@Valid @RequestBody BatchCreateRequest request) {
        Batch batch = batchService.createBatch(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(batch, "Batch created successfully"));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<Batch>>> getBatchesByProject(@PathVariable String projectId) {
        List<Batch> batches = batchService.getBatchesByProjectId(projectId);
        return ResponseEntity.ok(ApiResponse.success(batches, "Batches retrieved successfully"));
    }
}
