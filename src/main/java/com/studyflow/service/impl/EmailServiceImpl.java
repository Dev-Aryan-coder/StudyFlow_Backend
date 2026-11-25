package com.studyflow.service.impl;

import com.studyflow.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Async
    @Override
    public void sendAdmissionConfirmationEmail(String toEmail, String studentName, String instituteName, String batchTiming) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

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
            log.info("Admission confirmation email dispatched to: {}", toEmail);
        } catch (MessagingException e) {
            log.warn("Failed to send email to {}: {}", toEmail, e.getMessage());
        }
    }
}
