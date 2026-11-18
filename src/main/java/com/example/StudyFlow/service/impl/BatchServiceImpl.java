package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.dto.request.BatchCreateRequest;
import com.example.StudyFlow.entity.Batch;
import com.example.StudyFlow.entity.Project;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.BatchRepository;
import com.example.StudyFlow.repository.ProjectRepository;
import com.example.StudyFlow.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public Batch createBatch(BatchCreateRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + request.getProjectId()));

        Batch batch = Batch.builder()
                .project(project)
                .name(request.getName().trim())
                .subject(request.getSubject() != null ? request.getSubject().trim() : "General")
                .timingSlot(request.getTimingSlot().trim())
                .maxCapacity(request.getMaxCapacity() != null ? request.getMaxCapacity() : 40)
                .currentEnrollment(0)
                .active(true)
                .build();

        return batchRepository.save(batch);
    }

    @Override
    public List<Batch> getBatchesByProjectId(String projectId) {
        return batchRepository.findByProjectIdAndActiveTrue(projectId);
    }

    @Override
    public List<Batch> getBatchesBySlug(String slug) {
        return batchRepository.findByProjectSlugAndActiveTrue(slug);
    }
}
