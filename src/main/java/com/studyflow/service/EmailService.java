package com.studyflow.service;

public interface EmailService {

    void sendAdmissionConfirmationEmail(String toEmail, String studentName, String instituteName, String batchTiming);
}
