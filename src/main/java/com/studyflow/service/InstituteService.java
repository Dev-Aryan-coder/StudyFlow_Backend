package com.studyflow.service;

import com.studyflow.entity.Institute;
import com.studyflow.entity.InstituteConfig;

public interface InstituteService {

    Institute getBySlug(String slug);

    InstituteConfig getConfigBySlug(String slug);

    InstituteConfig updateConfig(String instituteId, String layoutData);
}
