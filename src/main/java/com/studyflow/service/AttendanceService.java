package com.studyflow.service;

import com.studyflow.dto.request.AttendanceMarkRequest;
import com.studyflow.entity.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    Attendance markAttendance(AttendanceMarkRequest request);

    List<Attendance> getBatchAttendance(String batchId, LocalDate date);

    List<Attendance> getStudentAttendanceHistory(String studentId);
}
