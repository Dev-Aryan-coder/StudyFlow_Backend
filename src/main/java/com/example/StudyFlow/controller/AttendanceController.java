package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.AttendanceMarkRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.entity.Attendance;
import com.example.StudyFlow.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    /**
     * 1-Click Attendance Marking (Present / Absent / Late)
     */
    @PostMapping("/mark")
    public ResponseEntity<ApiResponse<Attendance>> markAttendance(@Valid @RequestBody AttendanceMarkRequest request) {
        Attendance attendance = attendanceService.markAttendance(request);
        return ResponseEntity.ok(ApiResponse.success(attendance, "Attendance recorded successfully"));
    }

    /**
     * Get attendance for a batch on a specific date (Teacher View)
     */
    @GetMapping("/batch/{batchId}")
    public ResponseEntity<ApiResponse<List<Attendance>>> getBatchAttendance(
            @PathVariable String batchId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Attendance> list = attendanceService.getBatchAttendance(batchId, date);
        return ResponseEntity.ok(ApiResponse.success(list, "Batch attendance retrieved successfully"));
    }

    /**
     * Get attendance history for a student (Student & Parent View)
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<Attendance>>> getStudentAttendance(@PathVariable String studentId) {
        List<Attendance> history = attendanceService.getStudentAttendanceHistory(studentId);
        return ResponseEntity.ok(ApiResponse.success(history, "Student attendance history retrieved"));
    }
}
