package com.example.StudyFlow.service;

import com.example.StudyFlow.entity.Institute;
import com.example.StudyFlow.entity.InstituteConfig;

public interface InstituteService {

    Institute getBySlug(String slug);

    InstituteConfig getConfigBySlug(String slug);

    InstituteConfig updateConfig(String instituteId, String layoutData);
}

