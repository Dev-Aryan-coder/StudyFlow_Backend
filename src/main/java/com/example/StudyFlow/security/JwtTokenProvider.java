package com.example.StudyFlow.security;

import com.example.StudyFlow.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret:StudyFlowSuperSecretKey2026CoachingInstituteMasterKey}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms:86400000}") // 24 hours
    private long jwtExpirationMs;

    /**
     * Generates a signed HMAC-SHA256 token containing user identity and tenant context
     */
    public String generateToken(User user, String projectId) {
        long now = Instant.now().toEpochMilli();
        long expiry = now + jwtExpirationMs;

        String headerJson = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String payloadJson = String.format(
                "{\"sub\":\"%s\",\"email\":\"%s\",\"projectId\":\"%s\",\"iat\":%d,\"exp\":%d}",
                user.getId(),
                user.getEmail(),
                projectId != null ? projectId : "",
                now / 1000,
                expiry / 1000
        );

        String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(headerJson.getBytes(StandardCharsets.UTF_8));
        String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));

        String contentToSign = encodedHeader + "." + encodedPayload;
        String signature = sign(contentToSign, jwtSecret);

        return contentToSign + "." + signature;
    }

    /**
     * Overload for student login
     */
    public String generateStudentToken(String studentId, String name, String projectId) {
        long now = Instant.now().toEpochMilli();
        long expiry = now + jwtExpirationMs;

        String headerJson = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String payloadJson = String.format(
                "{\"sub\":\"%s\",\"name\":\"%s\",\"role\":\"STUDENT\",\"projectId\":\"%s\",\"iat\":%d,\"exp\":%d}",
                studentId,
                name,
                projectId != null ? projectId : "",
                now / 1000,
                expiry / 1000
        );

        String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(headerJson.getBytes(StandardCharsets.UTF_8));
        String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));

        String contentToSign = encodedHeader + "." + encodedPayload;
        String signature = sign(contentToSign, jwtSecret);

        return contentToSign + "." + signature;
    }

    public boolean validateToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return false;
            }
            String contentToSign = parts[0] + "." + parts[1];
            String expectedSignature = sign(contentToSign, jwtSecret);
            return expectedSignature.equals(parts[2]);
        } catch (Exception e) {
            return false;
        }
    }

    private String sign(String data, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKey);
            byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate token signature", e);
        }
    }
}
