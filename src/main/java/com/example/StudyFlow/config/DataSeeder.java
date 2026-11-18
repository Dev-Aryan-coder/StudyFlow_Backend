package com.example.StudyFlow.config;

import com.example.StudyFlow.entity.*;
import com.example.StudyFlow.enums.AttendanceStatus;
import com.example.StudyFlow.enums.LeadStatus;
import com.example.StudyFlow.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectConfigRepository configRepository;
    private final BatchRepository batchRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AttendanceRepository attendanceRepository;
    private final StudyMaterialRepository materialRepository;
    private final StudentLeadRepository leadRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("user@apexscience.in").isPresent()) {
            log.info("Demo data already seeded.");
            return;
        }

        log.info("Seeding realistic demo user & institute: Apex Science Academy...");

        // 1. Create Demo User
        User user = User.builder()
                .username("Dr. Arvind Sharma")
                .email("user@apexscience.in")
                .phoneNumber("9876543210")
                .className("Apex Science Academy")
                .passwordHash(passwordEncoder.encode("password123"))
                .active(true)
                .build();
        user = userRepository.save(user);

        // 2. Create Project
        Project project = Project.builder()
                .user(user)
                .projectName("Apex JEE & NEET 2026")
                .websiteName("Apex Science Academy")
                .slug("apex-science")
                .logoUrl("https://images.unsplash.com/photo-1546410531-bb4caa6b424d?w=200")
                .address("Plot 42, Near City Centre, Coaching Zone, Jaipur, Rajasthan 302001")
                .themeColor("#2563EB")
                .active(true)
                .build();
        project = projectRepository.save(project);

        // 3. Create Modular Layout Config
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
                    {"name": "Class 11 Target JEE", "timing": "Mon-Wed-Fri, 4:00 PM - 6:00 PM", "seats": 6},
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
                },
                "contact": {
                  "enabled": true,
                  "address": "Plot 42, Near City Centre, Coaching Zone, Jaipur",
                  "phoneNumber": "9876543210"
                }
              }
            }
            """;

        ProjectConfig config = ProjectConfig.builder()
                .project(project)
                .layoutData(initialLayoutJson)
                .build();
        configRepository.save(config);

        // 4. Create Batches
        Batch batch11 = Batch.builder()
                .project(project)
                .name("Class 11 Target JEE")
                .subject("Physics")
                .timingSlot("Mon-Wed-Fri, 4:00 PM - 6:00 PM")
                .maxCapacity(40)
                .currentEnrollment(28)
                .active(true)
                .build();
        batch11 = batchRepository.save(batch11);

        Batch batch12 = Batch.builder()
                .project(project)
                .name("Class 12 Boards Booster")
                .subject("Physics")
                .timingSlot("Tue-Thu-Sat, 6:00 PM - 8:00 PM")
                .maxCapacity(35)
                .currentEnrollment(31)
                .active(true)
                .build();
        batch12 = batchRepository.save(batch12);

        // 5. Create Teacher
        Teacher teacher = Teacher.builder()
                .project(project)
                .name("Dr. Arvind Sharma")
                .subject("Physics")
                .phoneNumber("9876543210")
                .email("arvind@apexscience.in")
                .qualification("Ph.D. Physics (Ex-Senior Faculty Allen Kota)")
                .active(true)
                .build();
        teacherRepository.save(teacher);

        // 6. Create Students
        Student s1 = Student.builder()
                .project(project)
                .batch(batch11)
                .name("Aryan Verma")
                .parentName("Suresh Verma")
                .phoneNumber("9811122233")
                .email("aryan.verma@example.com")
                .rollNumber("APX-2026-01")
                .pendingFees(new BigDecimal("4500.00"))
                .active(true)
                .build();
        s1 = studentRepository.save(s1);

        Student s2 = Student.builder()
                .project(project)
                .batch(batch11)
                .name("Sneha Patel")
                .parentName("Ramesh Patel")
                .phoneNumber("9822233344")
                .email("sneha.patel@example.com")
                .rollNumber("APX-2026-02")
                .pendingFees(BigDecimal.ZERO)
                .active(true)
                .build();
        s2 = studentRepository.save(s2);

        // 7. Attendance
        Attendance att = Attendance.builder()
                .student(s1)
                .batch(batch11)
                .attendanceDate(LocalDate.now())
                .status(AttendanceStatus.PRESENT)
                .remarks("Active participation in mechanics")
                .build();
        attendanceRepository.save(att);

        // 8. Study Material Notes
        StudyMaterial note = StudyMaterial.builder()
                .project(project)
                .batch(batch11)
                .title("Class 11 Physics: Kinematics & Laws of Motion (Formula Sheet)")
                .chapterName("Kinematics")
                .fileUrl("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf")
                .fileType("PDF")
                .publicLeadMagnet(true)
                .build();
        materialRepository.save(note);

        // 9. Lead
        StudentLead lead = StudentLead.builder()
                .project(project)
                .studentName("Rohit Mehra")
                .parentPhone("9899887766")
                .targetClass("Class 11 JEE")
                .message("Interested in joining from next Monday")
                .status(LeadStatus.NEW)
                .build();
        leadRepository.save(lead);

        log.info("Full demo data seeded successfully for: user@apexscience.in (password: password123)");
    }
}
