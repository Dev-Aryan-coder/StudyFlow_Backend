package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.*;
import com.example.StudyFlow.dto.response.UserResponse;

import java.util.Map;

public interface AuthService {

    UserResponse register(SignupRequest request);

    Map<String, Object> login(LoginRequest request);

    void sendPasswordResetOtp(ForgotPasswordSendOtpRequest request);

    boolean verifyPasswordResetOtp(ForgotPasswordVerifyOtpRequest request);

    void resetPassword(ForgotPasswordResetRequest request);
}
