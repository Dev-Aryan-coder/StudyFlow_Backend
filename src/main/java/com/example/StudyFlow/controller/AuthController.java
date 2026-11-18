package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.*;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.dto.response.UserResponse;
import com.example.StudyFlow.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Tuition Owner Signup / Registration
     * Collects: email, username, phoneNumber, password, className
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponse>> signup(@Valid @RequestBody SignupRequest request) {
        UserResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Account created successfully"));
    }

    /**
     * User Login: Sets Session Cookie (maxAge(-1), cleared on tab close) + returns JSON JWT
     * Requires: email, password
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse servletResponse) {
        Map<String, Object> response = authService.login(request);
        String token = (String) response.get("token");

        if (token != null) {
            // Strict Session Cookie: maxAge(-1) ensures the browser destroys it when the tab/browser is closed!
            ResponseCookie sessionCookie = ResponseCookie.from("studyflow_token", token)
                    .httpOnly(false)
                    .secure(false)
                    .path("/")
                    .maxAge(-1)
                    .sameSite("Lax")
                    .build();
            servletResponse.addHeader(HttpHeaders.SET_COOKIE, sessionCookie.toString());
        }

        return ResponseEntity.ok(ApiResponse.success(response, "Login successful"));
    }

    /**
     * User Logout: Clears the Session Cookie
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletResponse servletResponse) {
        ResponseCookie clearCookie = ResponseCookie.from("studyflow_token", "")
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        servletResponse.addHeader(HttpHeaders.SET_COOKIE, clearCookie.toString());

        return ResponseEntity.ok(ApiResponse.success(null, "Logged out successfully"));
    }

    /**
     * Forgot Password - Step 1: Validate email & send 6-digit OTP
     */
    @PostMapping("/forgot-password/send-otp")
    public ResponseEntity<ApiResponse<Void>> sendPasswordResetOtp(@Valid @RequestBody ForgotPasswordSendOtpRequest request) {
        authService.sendPasswordResetOtp(request);
        return ResponseEntity.ok(ApiResponse.success(null, "6-digit verification code has been sent to your email"));
    }

    /**
     * Forgot Password - Step 2: Verify the 6-digit OTP
     */
    @PostMapping("/forgot-password/verify-otp")
    public ResponseEntity<ApiResponse<Boolean>> verifyPasswordResetOtp(@Valid @RequestBody ForgotPasswordVerifyOtpRequest request) {
        boolean isValid = authService.verifyPasswordResetOtp(request);
        return ResponseEntity.ok(ApiResponse.success(isValid, "Verification code confirmed successfully"));
    }

    /**
     * Forgot Password - Step 3: Reset password with verified OTP
     */
    @PostMapping("/forgot-password/reset")
    public ResponseEntity<ApiResponse<Void>> resetPassword(@Valid @RequestBody ForgotPasswordResetRequest request) {
        authService.resetPassword(request);
        return ResponseEntity.ok(ApiResponse.success(null, "Password has been successfully updated. You can now log in."));
    }
}
