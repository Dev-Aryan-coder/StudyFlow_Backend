package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.StudentCreateRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.entity.Student;
import com.example.StudyFlow.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@Valid @RequestBody StudentCreateRequest request) {
        Student student = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(student, "Student enrolled successfully"));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<Student>>> getStudentsByProject(@PathVariable String projectId) {
        List<Student> students = studentService.getStudentsByProjectId(projectId);
        return ResponseEntity.ok(ApiResponse.success(students, "Students retrieved successfully"));
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<ApiResponse<List<Student>>> getStudentsByBatch(@PathVariable String batchId) {
        List<Student> students = studentService.getStudentsByBatchId(batchId);
        return ResponseEntity.ok(ApiResponse.success(students, "Batch students retrieved successfully"));
    }
}
