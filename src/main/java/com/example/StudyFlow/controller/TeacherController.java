package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.TeacherCreateRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.entity.Teacher;
import com.example.StudyFlow.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<ApiResponse<Teacher>> createTeacher(@Valid @RequestBody TeacherCreateRequest request) {
        Teacher teacher = teacherService.createTeacher(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(teacher, "Faculty member added successfully"));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<Teacher>>> getTeachersByProject(@PathVariable String projectId) {
        List<Teacher> teachers = teacherService.getTeachersByProjectId(projectId);
        return ResponseEntity.ok(ApiResponse.success(teachers, "Teachers retrieved successfully"));
    }
}
