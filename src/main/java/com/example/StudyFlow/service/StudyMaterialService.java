package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.StudyMaterialUploadRequest;
import com.example.StudyFlow.entity.StudyMaterial;

import java.util.List;

public interface StudyMaterialService {

    StudyMaterial uploadMaterial(StudyMaterialUploadRequest request);

    List<StudyMaterial> getMaterialsByProjectId(String projectId);

    List<StudyMaterial> getMaterialsByBatchId(String batchId);

    List<StudyMaterial> getPublicLeadMagnets(String projectId);
}
