package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.DynamicLoginRequest;
import com.example.StudyFlow.dto.request.StudentLeadRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.dto.response.DynamicLoginResponse;
import com.example.StudyFlow.entity.*;
import com.example.StudyFlow.repository.StudentRepository;
import com.example.StudyFlow.repository.UserRepository;
import com.example.StudyFlow.security.JwtTokenProvider;
import com.example.StudyFlow.service.BatchService;
import com.example.StudyFlow.service.LeadService;
import com.example.StudyFlow.service.ProjectService;
import com.example.StudyFlow.service.WebsiteLayoutService;
import com.example.StudyFlow.service.StudyMaterialService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {

    private final ProjectService projectService;
    private final WebsiteLayoutService websiteLayoutService;
    private final LeadService leadService;
    private final BatchService batchService;
    private final StudyMaterialService materialService;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Resolves the live public coaching website by its unique URL slug
     * Example: GET /api/public/apex-science
     */
    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPublicSite(@PathVariable String slug) {
        Project project = projectService.getBySlug(slug);
        String layoutJson = websiteLayoutService.getPublishedLayoutBySlug(slug);

        Map<String, Object> data = new HashMap<>();
        data.put("id", project.getId());
        data.put("projectName", project.getProjectName());
        data.put("websiteName", project.getWebsiteName());
        data.put("slug", project.getSlug());
        data.put("logoUrl", project.getLogoUrl());
        data.put("address", project.getAddress());
        data.put("themeColor", project.getThemeColor());
        data.put("tutorName", project.getUser() != null ? project.getUser().getUsername() : "");
        data.put("whatsappNumber", project.getUser() != null ? project.getUser().getPhoneNumber() : "");
        data.put("layout", layoutJson);

        return ResponseEntity.ok(ApiResponse.success(data, "Public website resolved successfully"));
    }

    /**
     * Resolves a specific Welcoming Page for this institute (Home, About, Testimonials, Service, Contact)
     * Example: GET /api/public/apex-science/page/about
     */
    @GetMapping("/{slug}/page/{pageName}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPublicPage(
            @PathVariable String slug,
            @PathVariable String pageName) {
        Project project = projectService.getBySlug(slug);
        String layoutJson = websiteLayoutService.getPublishedLayoutBySlug(slug);

        Map<String, Object> pageData = new HashMap<>();
        pageData.put("slug", slug);
        pageData.put("instituteName", project.getWebsiteName());
        pageData.put("pageName", pageName);
        pageData.put("layout", layoutJson);

        return ResponseEntity.ok(ApiResponse.success(pageData, "Page '" + pageName + "' resolved successfully"));
    }

    /**
     * Dynamic Tenant-Scoped Public Auth Gateway
     * Accepts Student ID (Roll No), Mobile Phone, or Email + Password
     * Sets Session Cookie (destroyed on tab close) + returns JSON JWT
     * Example: POST /api/public/apex-science/auth/login
     */
    @PostMapping("/{slug}/auth/login")
    public ResponseEntity<ApiResponse<DynamicLoginResponse>> tenantScopedLogin(
            @PathVariable String slug,
            @Valid @RequestBody DynamicLoginRequest request,
            HttpServletResponse servletResponse) {

        log.info("Processing tenant-scoped login for slug: '{}' with identifier: '{}'", slug, request.getIdentifier());

        // 1. Resolve Institute Tenant
        Project project = projectService.getBySlug(slug);

        // 2. Check Student Records first
        Optional<Student> studentOpt = studentRepository.findByProjectAndIdentifier(project.getId(), request.getIdentifier());
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            String token = jwtTokenProvider.generateStudentToken(student.getId(), student.getName(), project.getId());

            // Set Session Cookie (maxAge(-1) ensures destruction on tab close)
            ResponseCookie sessionCookie = ResponseCookie.from("studyflow_token", token)
                    .httpOnly(false)
                    .secure(false)
                    .path("/")
                    .maxAge(-1)
                    .sameSite("Lax")
                    .build();
            servletResponse.addHeader(HttpHeaders.SET_COOKIE, sessionCookie.toString());

            DynamicLoginResponse response = DynamicLoginResponse.builder()
                    .token(token)
                    .userId(student.getId())
                    .name(student.getName())
                    .role("STUDENT")
                    .studentId(student.getRollNumber())
                    .email(student.getEmail())
                    .instituteName(project.getWebsiteName())
                    .redirectUrl("/student/dashboard")
                    .build();

            return ResponseEntity.ok(ApiResponse.success(response, "Student authenticated successfully"));
        }

        // 3. Check Institute Owner / Admin Records
        Optional<User> userOpt = userRepository.findByIdentifier(request.getIdentifier());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
                String token = jwtTokenProvider.generateToken(user, project.getId());

                // Set Session Cookie
                ResponseCookie sessionCookie = ResponseCookie.from("studyflow_token", token)
                        .httpOnly(false)
                        .secure(false)
                        .path("/")
                        .maxAge(-1)
                        .sameSite("Lax")
                        .build();
                servletResponse.addHeader(HttpHeaders.SET_COOKIE, sessionCookie.toString());

                DynamicLoginResponse response = DynamicLoginResponse.builder()
                        .token(token)
                        .userId(user.getId())
                        .name(user.getUsername())
                        .role("ADMIN")
                        .email(user.getEmail())
                        .instituteName(project.getWebsiteName())
                        .redirectUrl("/dashboard")
                        .build();

                return ResponseEntity.ok(ApiResponse.success(response, "Admin authenticated successfully"));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Invalid Roll Number / Email or Password"));
    }

    /**
     * Captures incoming demo class inquiries from public website (Scoped endpoint)
     * Includes invisible honeypot anti-bot shield
     */
    @PostMapping("/{slug}/inquire")
    public ResponseEntity<ApiResponse<Map<String, Object>>> submitInquiry(
            @PathVariable String slug,
            @Valid @RequestBody StudentLeadRequest request,
            @RequestParam(required = false) String hpField) {

        // Anti-Bot Honeypot Tripwire
        if (hpField != null && !hpField.trim().isEmpty()) {
            log.warn("Bot detected via honeypot trap on slug: {}", slug);
            Map<String, Object> fakeResponse = new HashMap<>();
            fakeResponse.put("status", "SUCCESS");
            return ResponseEntity.ok(ApiResponse.success(fakeResponse, "Inquiry received"));
        }

        request.setInstituteSlug(slug);
        return submitLead(request);
    }

    /**
     * Captures incoming demo class inquiries from public website
     * Saves lead into MySQL, sends async email alert, and generates WhatsApp deep-link
     */
    @PostMapping("/lead")
    public ResponseEntity<ApiResponse<Map<String, Object>>> submitLead(@Valid @RequestBody StudentLeadRequest request) {
        StudentLead lead = leadService.createLead(request);

        // Generate WhatsApp deep-link URL for immediate redirect
        String tutorPhone = lead.getProject().getUser() != null ? lead.getProject().getUser().getPhoneNumber() : "";
        String cleanPhone = tutorPhone.replaceAll("[^0-9]", "");
        if (!cleanPhone.startsWith("91") && cleanPhone.length() == 10) {
            cleanPhone = "91" + cleanPhone;
        }

        String whatsappMsg = "Hello! I booked a demo class for " + lead.getStudentName() + 
                " (" + (lead.getTargetClass() != null ? lead.getTargetClass() : "Coaching") + ") on StudyFlow.";
        String encodedMsg = URLEncoder.encode(whatsappMsg, StandardCharsets.UTF_8);
        String whatsappUrl = "https://wa.me/" + cleanPhone + "?text=" + encodedMsg;

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("leadId", lead.getId());
        responseData.put("studentName", lead.getStudentName());
        responseData.put("whatsappUrl", whatsappUrl);
        responseData.put("status", lead.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(responseData, "Admission enquiry submitted! Redirecting to WhatsApp."));
    }

    /**
     * Get published batches for this institute
     */
    @GetMapping("/{slug}/batches")
    public ResponseEntity<ApiResponse<List<Batch>>> getPublicBatches(@PathVariable String slug) {
        List<Batch> batches = batchService.getBatchesBySlug(slug);
        return ResponseEntity.ok(ApiResponse.success(batches, "Batches retrieved successfully"));
    }

    /**
     * Get free sample study notes (Lead Magnets)
     */
    @GetMapping("/{slug}/notes")
    public ResponseEntity<ApiResponse<List<StudyMaterial>>> getPublicNotes(@PathVariable String slug) {
        Project project = projectService.getBySlug(slug);
        List<StudyMaterial> notes = materialService.getPublicLeadMagnets(project.getId());
        return ResponseEntity.ok(ApiResponse.success(notes, "Sample study notes retrieved successfully"));
    }
}
