package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.dto.request.*;
import com.example.StudyFlow.dto.response.UserResponse;
import com.example.StudyFlow.entity.PasswordResetOtp;
import com.example.StudyFlow.entity.User;
import com.example.StudyFlow.exception.BadRequestException;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.PasswordResetOtpRepository;
import com.example.StudyFlow.repository.UserRepository;
import com.example.StudyFlow.security.JwtTokenProvider;
import com.example.StudyFlow.service.AuthService;
import com.example.StudyFlow.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordResetOtpRepository otpRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    @Transactional
    public UserResponse register(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("An account already exists with this email address: " + request.getEmail());
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username is already taken: " + request.getUsername());
        }

        User user = User.builder()
                .username(request.getUsername().trim())
                .email(request.getEmail().trim().toLowerCase())
                .phoneNumber(request.getPhoneNumber().trim())
                .className(request.getClassName().trim())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .build();

        User savedUser = userRepository.save(user);
        log.info("New tuition owner registered successfully: {} ({})", savedUser.getUsername(), savedUser.getEmail());

        return mapToUserResponse(savedUser);
    }

    @Override
    public Map<String, Object> login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail().trim().toLowerCase())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid email or password");
        }

        // Generate signed JWT token
        String token = jwtTokenProvider.generateToken(user, null);

        Map<String, Object> authData = new HashMap<>();
        authData.put("token", token);
        authData.put("user", mapToUserResponse(user));

        log.info("User {} logged in successfully with JWT token", user.getEmail());
        return authData;
    }

    @Override
    @Transactional
    public void sendPasswordResetOtp(ForgotPasswordSendOtpRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No account found with email address: " + email));

        // Generate 6-digit cryptographically secure OTP
        int number = 100000 + secureRandom.nextInt(900000);
        String otpCode = String.valueOf(number);

        // Store OTP with 10-minute expiry
        PasswordResetOtp otp = PasswordResetOtp.builder()
                .email(email)
                .otpCode(otpCode)
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .used(false)
                .build();

        otpRepository.save(otp);

        // Dispatch OTP via JavaMailSender
        emailService.sendOtpEmail(email, otpCode);
        log.info("Password reset OTP generated and emailed to {}", email);
    }

    @Override
    public boolean verifyPasswordResetOtp(ForgotPasswordVerifyOtpRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        String otpCode = request.getOtp().trim();

        PasswordResetOtp otp = otpRepository.findByEmailAndOtpCodeAndUsedFalse(email, otpCode)
                .orElseThrow(() -> new BadRequestException("Invalid or expired verification code"));

        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Verification code has expired. Please request a new one.");
        }

        return true;
    }

    @Override
    @Transactional
    public void resetPassword(ForgotPasswordResetRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        String otpCode = request.getOtp().trim();

        PasswordResetOtp otp = otpRepository.findByEmailAndOtpCodeAndUsedFalse(email, otpCode)
                .orElseThrow(() -> new BadRequestException("Invalid or expired verification code"));

        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Verification code has expired. Please request a new one.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No account found with email address: " + email));

        // Update password hash
        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        // Invalidate OTP
        otp.setUsed(true);
        otpRepository.save(otp);

        // Send confirmation email
        emailService.sendPasswordResetSuccessEmail(email, user.getUsername());
        log.info("Password successfully reset for user: {}", email);
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .className(user.getClassName())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
