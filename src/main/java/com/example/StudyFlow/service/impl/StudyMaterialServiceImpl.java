package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.dto.request.StudyMaterialUploadRequest;
import com.example.StudyFlow.entity.Batch;
import com.example.StudyFlow.entity.Project;
import com.example.StudyFlow.entity.StudyMaterial;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.BatchRepository;
import com.example.StudyFlow.repository.ProjectRepository;
import com.example.StudyFlow.repository.StudyMaterialRepository;
import com.example.StudyFlow.service.StudyMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyMaterialServiceImpl implements StudyMaterialService {

    private final StudyMaterialRepository materialRepository;
    private final ProjectRepository projectRepository;
    private final BatchRepository batchRepository;

    @Override
    @Transactional
    public StudyMaterial uploadMaterial(StudyMaterialUploadRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found: " + request.getProjectId()));

        Batch batch = null;
        if (request.getBatchId() != null && !request.getBatchId().isBlank()) {
            batch = batchRepository.findById(request.getBatchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Batch not found: " + request.getBatchId()));
        }

        StudyMaterial material = StudyMaterial.builder()
                .project(project)
                .batch(batch)
                .title(request.getTitle().trim())
                .chapterName(request.getChapterName())
                .fileUrl(request.getFileUrl().trim())
                .fileType(request.getFileType() != null ? request.getFileType() : "PDF")
                .publicLeadMagnet(request.isPublicLeadMagnet())
                .build();

        return materialRepository.save(material);
    }

    @Override
    public List<StudyMaterial> getMaterialsByProjectId(String projectId) {
        return materialRepository.findByProjectIdOrderByCreatedAtDesc(projectId);
    }

    @Override
    public List<StudyMaterial> getMaterialsByBatchId(String batchId) {
        return materialRepository.findByBatchIdOrderByCreatedAtDesc(batchId);
    }

    @Override
    public List<StudyMaterial> getPublicLeadMagnets(String projectId) {
        return materialRepository.findByProjectIdAndPublicLeadMagnetTrue(projectId);
    }
}
