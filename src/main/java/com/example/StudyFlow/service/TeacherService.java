package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.TeacherCreateRequest;
import com.example.StudyFlow.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher createTeacher(TeacherCreateRequest request);

    List<Teacher> getTeachersByProjectId(String projectId);
}
