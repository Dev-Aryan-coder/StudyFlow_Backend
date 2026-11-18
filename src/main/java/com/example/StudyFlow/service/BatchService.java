package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.BatchCreateRequest;
import com.example.StudyFlow.entity.Batch;

import java.util.List;

public interface BatchService {

    Batch createBatch(BatchCreateRequest request);

    List<Batch> getBatchesByProjectId(String projectId);

    List<Batch> getBatchesBySlug(String slug);
}
