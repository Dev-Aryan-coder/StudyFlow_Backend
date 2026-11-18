package com.example.StudyFlow.repository;

import com.example.StudyFlow.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {

    List<Attendance> findByBatchIdAndAttendanceDate(String batchId, LocalDate date);

    List<Attendance> findByStudentIdOrderByAttendanceDateDesc(String studentId);

    Optional<Attendance> findByStudentIdAndBatchIdAndAttendanceDate(String studentId, String batchId, LocalDate date);
}

