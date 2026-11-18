package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.dto.request.StudentCreateRequest;
import com.example.StudyFlow.entity.Batch;
import com.example.StudyFlow.entity.Project;
import com.example.StudyFlow.entity.Student;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.BatchRepository;
import com.example.StudyFlow.repository.ProjectRepository;
import com.example.StudyFlow.repository.StudentRepository;
import com.example.StudyFlow.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;
    private final BatchRepository batchRepository;

    @Override
    @Transactional
    public Student createStudent(StudentCreateRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found: " + request.getProjectId()));

        Batch batch = null;
        if (request.getBatchId() != null && !request.getBatchId().isBlank()) {
            batch = batchRepository.findById(request.getBatchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Batch not found: " + request.getBatchId()));
        }

        Student student = Student.builder()
                .project(project)
                .batch(batch)
                .name(request.getName().trim())
                .parentName(request.getParentName())
                .phoneNumber(request.getPhoneNumber().trim())
                .email(request.getEmail())
                .rollNumber(request.getRollNumber())
                .pendingFees(request.getPendingFees())
                .active(true)
                .build();

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsByProjectId(String projectId) {
        return studentRepository.findByProjectIdAndActiveTrue(projectId);
    }

    @Override
    public List<Student> getStudentsByBatchId(String batchId) {
        return studentRepository.findByBatchIdAndActiveTrue(batchId);
    }
}
