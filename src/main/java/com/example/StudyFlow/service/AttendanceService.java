package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.AttendanceMarkRequest;
import com.example.StudyFlow.entity.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    Attendance markAttendance(AttendanceMarkRequest request);

    List<Attendance> getBatchAttendance(String batchId, LocalDate date);

    List<Attendance> getStudentAttendanceHistory(String studentId);
}

