package com.example.StudyFlow.service;

public interface EmailService {

    void sendOtpEmail(String toEmail, String otpCode);

    void sendPasswordResetSuccessEmail(String toEmail, String username);

    void sendAdmissionConfirmationEmail(String toEmail, String studentName, String instituteName, String batchTiming);
}
