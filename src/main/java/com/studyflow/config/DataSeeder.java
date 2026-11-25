package com.studyflow.config;

import com.studyflow.entity.*;
import com.studyflow.enums.AttendanceStatus;
import com.studyflow.enums.PlanTier;
import com.studyflow.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final InstituteRepository instituteRepository;
    private final InstituteConfigRepository configRepository;
    private final BatchRepository batchRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public void run(String... args) throws Exception {
        if (instituteRepository.findBySlug("apex-science").isPresent()) {
            log.info("Demo data already seeded.");
            return;
        }

        log.info("Seeding realistic demo institute: Apex Science Academy...");

        // 1. Create Institute
        Institute institute = Institute.builder()
                .name("Apex Science Academy")
                .slug("apex-science")
                .ownerName("Dr. Arvind Sharma")
                .phoneNumber("9876543210")
                .whatsappNumber("9876543210")
                .email("contact@apexscience.in")
                .passwordHash("$2a$12$dummyHashPasswordForDemo")
                .planTier(PlanTier.PRO)
                .themeColor("#2563EB")
                .active(true)
                .build();
        institute = instituteRepository.save(institute);

        // 2. Create Layout Config
        String initialLayoutJson = """
            {
              "theme": {
                "primaryColor": "#2563EB",
                "fontFamily": "Inter"
              },
              "modules": {
                "hero": {
                  "enabled": true,
                  "title": "Crack JEE & NEET with Kota's Top Faculty",
                  "subtitle": "Conceptual clarity, daily problem sheets, and personal mentorship.",
                  "teacherName": "Dr. Arvind Sharma (Ex-Allen, 18+ Yrs Exp)",
                  "badge": "Admissions Open for 2026-27"
                },
                "batches": {
                  "enabled": true,
                  "items": [
                    {"name": "Class 11 JEE Pinnacle", "timing": "Mon-Wed-Fri, 4:00 PM - 6:00 PM", "seats": 6},
                    {"name": "Class 12 Boards Booster", "timing": "Tue-Thu-Sat, 6:00 PM - 8:00 PM", "seats": 4}
                  ]
                },
                "toppers": {
                  "enabled": true,
                  "items": [
                    {"name": "Aman Verma", "score": "99.6%ile JEE Main 2024"},
                    {"name": "Priya Nair", "score": "685/720 NEET 2024"}
                  ]
                },
                "demoBooking": {
                  "enabled": true,
                  "buttonText": "Book Free 2-Day Demo Class"
                },
                "feeStructure": {
                  "enabled": true,
                  "monthlyFee": "₹4,500 / month",
                  "yearlyFee": "₹45,000 / year (Save 15%)"
                }
              }
            }
            """;

        InstituteConfig config = InstituteConfig.builder()
                .institute(institute)
                .layoutData(initialLayoutJson)
                .build();
        configRepository.save(config);

        // 3. Create Batches
        Batch batch11 = Batch.builder()
                .institute(institute)
                .name("Class 11 Target JEE")
                .subject("Physics")
                .timingSlot("Mon-Wed-Fri, 4:00 PM - 6:00 PM")
                .maxCapacity(40)
                .currentEnrollment(28)
                .active(true)
                .build();
        batch11 = batchRepository.save(batch11);

        // 4. Create Teacher
        Teacher teacher = Teacher.builder()
                .institute(institute)
                .name("Dr. Arvind Sharma")
                .subject("Physics")
                .phoneNumber("9876543210")
                .qualification("Ph.D. Physics (Ex-Senior Faculty Allen Kota)")
                .active(true)
                .build();
        teacherRepository.save(teacher);

        // 5. Create Sample Students
        Student student1 = Student.builder()
                .institute(institute)
                .batch(batch11)
                .name("Aryan Verma")
                .parentName("Suresh Verma")
                .phoneNumber("9811122233")
                .rollNumber("APX-2026-01")
                .pendingFees(new BigDecimal("4500.00"))
                .active(true)
                .build();
        student1 = studentRepository.save(student1);

        Student student2 = Student.builder()
                .institute(institute)
                .batch(batch11)
                .name("Sneha Patel")
                .parentName("Ramesh Patel")
                .phoneNumber("9822233344")
                .rollNumber("APX-2026-02")
                .pendingFees(BigDecimal.ZERO)
                .active(true)
                .build();
        student2 = studentRepository.save(student2);

        // 6. Create Attendance Record
        Attendance att1 = Attendance.builder()
                .student(student1)
                .batch(batch11)
                .attendanceDate(LocalDate.now())
                .status(AttendanceStatus.PRESENT)
                .remarks("Active participation in mechanics problems")
                .build();
        attendanceRepository.save(att1);

        log.info("Demo institute seeding completed successfully!");
    }
}
