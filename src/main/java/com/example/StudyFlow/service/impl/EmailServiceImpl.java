package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:yonexffyt14@gmail.com}")
    private String senderEmail;

    @Async
    @Override
    public void sendOtpEmail(String toEmail, String otpCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail, "StudyFlow");
            helper.setTo(toEmail);
            helper.setSubject("StudyFlow — Your Password Reset Verification Code");

            String html = """
                <div style="font-family: Arial, sans-serif; padding: 25px; color: #1e293b; background: #f8fafc; border-radius: 12px; max-width: 500px; margin: auto;">
                    <h2 style="color: #2563eb; margin-top: 0;">StudyFlow Account Security</h2>
                    <p>We received a request to reset your password. Use the verification code below to complete the 2-step verification process:</p>
                    <div style="background: #ffffff; border: 2px dashed #2563eb; padding: 15px; text-align: center; border-radius: 8px; margin: 20px 0;">
                        <span style="font-size: 32px; font-weight: bold; letter-spacing: 6px; color: #1e293b;">%s</span>
                    </div>
                    <p style="color: #64748b; font-size: 13px;">This code is valid for <strong>10 minutes</strong>. If you did not request this, please ignore this email.</p>
                    <hr style="border: 0; border-top: 1px solid #e2e8f0; margin: 20px 0;" />
                    <p style="font-size: 12px; color: #94a3b8;">StudyFlow &copy; 2026. Empowering Local Coaching Institutes.</p>
                </div>
                """.formatted(otpCode);

            helper.setText(html, true);
            mailSender.send(message);
            log.info("Password reset OTP email sent successfully to: {}", toEmail);
        } catch (Exception e) {
            log.warn("Failed to dispatch OTP email to {}: {}", toEmail, e.getMessage());
        }
    }

    @Async
    @Override
    public void sendPasswordResetSuccessEmail(String toEmail, String username) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail, "StudyFlow");
            helper.setTo(toEmail);
            helper.setSubject("StudyFlow — Password Successfully Reset");

            String html = """
                <div style="font-family: Arial, sans-serif; padding: 20px; color: #333;">
                    <h3 style="color: #10B981;">Password Updated</h3>
                    <p>Hello <strong>%s</strong>,</p>
                    <p>Your password for your StudyFlow account has been successfully reset.</p>
                    <p>If you did not perform this action, please contact support immediately.</p>
                </div>
                """.formatted(username);

            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
            log.warn("Failed to dispatch password change confirmation: {}", e.getMessage());
        }
    }

    @Async
    @Override
    public void sendAdmissionConfirmationEmail(String toEmail, String studentName, String instituteName, String batchTiming) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail, "StudyFlow");
            helper.setTo(toEmail);
            helper.setSubject("New Admission Enquiry for " + instituteName);

            String html = """
                <div style="font-family: Arial, sans-serif; padding: 20px;">
                    <h2 style="color: #2563EB;">%s — New Admission Enquiry</h2>
                    <p>A new student has registered for a demo class!</p>
                    <p><strong>Student Name:</strong> %s</p>
                    <p><strong>Target Class / Batch:</strong> %s</p>
                    <p>Log in to your StudyFlow dashboard to view contact details.</p>
                </div>
                """.formatted(instituteName, studentName, batchTiming);

            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
            log.warn("Failed to send admission confirmation: {}", e.getMessage());
        }
    }
}
