package com.example.StudyFlow.dto.request;

import com.example.StudyFlow.enums.AttendanceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceMarkRequest {

    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Batch ID is required")
    private String batchId;

    @NotNull(message = "Date is required")
    private LocalDate attendanceDate;

    @NotNull(message = "Attendance status is required")
    private AttendanceStatus status;

    private String remarks;
}

