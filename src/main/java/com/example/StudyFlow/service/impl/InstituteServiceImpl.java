package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.entity.Institute;
import com.example.StudyFlow.entity.InstituteConfig;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.InstituteConfigRepository;
import com.example.StudyFlow.repository.InstituteRepository;
import com.example.StudyFlow.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {

    private final InstituteRepository instituteRepository;
    private final InstituteConfigRepository configRepository;

    @Override
    public Institute getBySlug(String slug) {
        return instituteRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Institute not found with slug: " + slug));
    }

    @Override
    public InstituteConfig getConfigBySlug(String slug) {
        return configRepository.findByInstituteSlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found for institute: " + slug));
    }

    @Override
    @Transactional
    public InstituteConfig updateConfig(String instituteId, String layoutData) {
        InstituteConfig config = configRepository.findByInstituteId(instituteId)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found for institute id: " + instituteId));

        config.setLayoutData(layoutData);
        return configRepository.save(config);
    }
}

