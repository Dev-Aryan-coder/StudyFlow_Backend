package com.studyflow.service.impl;

import com.studyflow.dto.request.AttendanceMarkRequest;
import com.studyflow.entity.Attendance;
import com.studyflow.entity.Batch;
import com.studyflow.entity.Student;
import com.studyflow.exception.ResourceNotFoundException;
import com.studyflow.repository.AttendanceRepository;
import com.studyflow.repository.BatchRepository;
import com.studyflow.repository.StudentRepository;
import com.studyflow.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;

    @Override
    @Transactional
    public Attendance markAttendance(AttendanceMarkRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + request.getStudentId()));
        Batch batch = batchRepository.findById(request.getBatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found: " + request.getBatchId()));

        Attendance attendance = attendanceRepository.findByStudentIdAndBatchIdAndAttendanceDate(
                student.getId(), batch.getId(), request.getAttendanceDate()
        ).orElse(Attendance.builder()
                .student(student)
                .batch(batch)
                .attendanceDate(request.getAttendanceDate())
                .build());

        attendance.setStatus(request.getStatus());
        attendance.setRemarks(request.getRemarks());

        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getBatchAttendance(String batchId, LocalDate date) {
        return attendanceRepository.findByBatchIdAndAttendanceDate(batchId, date);
    }

    @Override
    public List<Attendance> getStudentAttendanceHistory(String studentId) {
        return attendanceRepository.findByStudentIdOrderByAttendanceDateDesc(studentId);
    }
}
