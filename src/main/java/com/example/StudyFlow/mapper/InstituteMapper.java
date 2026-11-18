package com.example.StudyFlow.mapper;

import com.example.StudyFlow.entity.Institute;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InstituteMapper {

    public Map<String, Object> toSummaryMap(Institute institute) {
        if (institute == null) return null;
        Map<String, Object> map = new HashMap<>();
        map.put("id", institute.getId());
        map.put("name", institute.getName());
        map.put("slug", institute.getSlug());
        map.put("ownerName", institute.getOwnerName());
        map.put("whatsappNumber", institute.getWhatsappNumber());
        map.put("planTier", institute.getPlanTier());
        map.put("themeColor", institute.getThemeColor());
        return map;
    }
}

