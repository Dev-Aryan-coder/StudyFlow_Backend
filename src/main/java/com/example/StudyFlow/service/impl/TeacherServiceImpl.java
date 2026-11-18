package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.dto.request.TeacherCreateRequest;
import com.example.StudyFlow.entity.Project;
import com.example.StudyFlow.entity.Teacher;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.ProjectRepository;
import com.example.StudyFlow.repository.TeacherRepository;
import com.example.StudyFlow.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public Teacher createTeacher(TeacherCreateRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found: " + request.getProjectId()));

        Teacher teacher = Teacher.builder()
                .project(project)
                .name(request.getName().trim())
                .subject(request.getSubject().trim())
                .phoneNumber(request.getPhoneNumber().trim())
                .email(request.getEmail())
                .qualification(request.getQualification())
                .active(true)
                .build();

        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getTeachersByProjectId(String projectId) {
        return teacherRepository.findByProjectIdAndActiveTrue(projectId);
    }
}
