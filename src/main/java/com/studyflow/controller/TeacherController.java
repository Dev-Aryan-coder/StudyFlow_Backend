package com.studyflow.controller;

import com.studyflow.dto.request.AttendanceMarkRequest;
import com.studyflow.dto.response.ApiResponse;
import com.studyflow.entity.Attendance;
import com.studyflow.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final AttendanceService attendanceService;

    @PostMapping("/attendance")
    public ResponseEntity<ApiResponse<Attendance>> markAttendance(@Valid @RequestBody AttendanceMarkRequest request) {
        Attendance attendance = attendanceService.markAttendance(request);
        return ResponseEntity.ok(ApiResponse.success(attendance, "Attendance marked successfully"));
    }

    @GetMapping("/attendance/{batchId}")
    public ResponseEntity<ApiResponse<List<Attendance>>> getBatchAttendance(
            @PathVariable String batchId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Attendance> list = attendanceService.getBatchAttendance(batchId, date);
        return ResponseEntity.ok(ApiResponse.success(list, "Batch attendance retrieved successfully"));
    }
}
