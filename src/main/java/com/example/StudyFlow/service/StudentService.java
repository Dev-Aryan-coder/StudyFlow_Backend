package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.StudentCreateRequest;
import com.example.StudyFlow.entity.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(StudentCreateRequest request);

    List<Student> getStudentsByProjectId(String projectId);

    List<Student> getStudentsByBatchId(String batchId);
}
