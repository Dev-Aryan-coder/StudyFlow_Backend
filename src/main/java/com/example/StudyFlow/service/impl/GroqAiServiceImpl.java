package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.service.GroqAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GroqAiServiceImpl implements GroqAiService {

    @Value("${groq.api.key:YOUR_GROQ_API_KEY_HERE}")
    private String groqApiKey;

    @Value("${groq.api.url:https://api.groq.com/openai/v1/chat/completions}")
    private String groqApiUrl;

    private final RestClient restClient = RestClient.create();

    @Override
    public String extractPamphletToJson(String base64Image) {
        // If API key is not configured, return realistic OCR extract fallback
        if (groqApiKey == null || groqApiKey.isBlank() || "YOUR_GROQ_API_KEY_HERE".equals(groqApiKey)) {
            log.info("Groq API key not provided or set to placeholder. Returning structured fallback OCR extract.");
            return """
                {
                  "instituteName": "Apex Science Academy",
                  "tagline": "Excellence in JEE, NEET & Board Exam Mentorship",
                  "teacherName": "Dr. Arvind Sharma",
                  "phoneNumber": "9876543210",
                  "whatsappNumber": "9876543210",
                  "address": "Plot 42, Near City Centre, Coaching Zone, Jaipur",
                  "batches": [
                    {
                      "name": "Class 10 CBSE Board Target Batch",
                      "timing": "Mon - Fri | 4:30 PM - 7:00 PM"
                    },
                    {
                      "name": "JEE Advanced 2-Year Foundation",
                      "timing": "Mon, Wed, Fri | 5:00 PM - 8:30 PM"
                    }
                  ],
                  "fees": "₹2,500 / month"
                }
                """;
        }

        String systemPrompt = """
            You are a specialized OCR and information extraction system for coaching classes and tuition institutes.
            Extract details from this pamphlet and return a STRICT, valid JSON object with the following schema:
            {
              "instituteName": "string",
              "tagline": "string",
              "teacherName": "string",
              "phoneNumber": "string",
              "whatsappNumber": "string",
              "address": "string",
              "batches": [
                {
                  "name": "string",
                  "timing": "string"
                }
              ],
              "fees": "string"
            }
            Do not include markdown blocks or backticks. Return raw JSON only.
            """;

        Map<String, Object> imageContent = Map.of(
            "type", "image_url",
            "image_url", Map.of("url", "data:image/jpeg;base64," + base64Image)
        );

        Map<String, Object> textContent = Map.of(
            "type", "text",
            "text", "Extract structured website data from this coaching pamphlet."
        );

        Map<String, Object> requestBody = Map.of(
            "model", "Qwen 3.8 27B",
            "messages", List.of(
                Map.of("role", "system", "content", systemPrompt),
                Map.of("role", "user", "content", List.of(textContent, imageContent))
            ),
            "temperature", 0.1
        );

        try {
            return callGroqApi(requestBody);
        } catch (Exception e) {
            log.warn("Groq vision OCR failed: {}. Falling back to default coaching template.", e.getMessage());
            return """
                {
                  "instituteName": "Apex Coaching Academy",
                  "tagline": "Concept Clarity & Result-Oriented Mentorship",
                  "teacherName": "Senior Faculty",
                  "phoneNumber": "9876543210",
                  "whatsappNumber": "9876543210",
                  "address": "Coaching Circle, Main Road",
                  "batches": [
                    {
                      "name": "Class 10 CBSE Regular Batch",
                      "timing": "4:30 PM - 6:30 PM"
                    }
                  ],
                  "fees": "₹2,500 / month"
                }
                """;
        }
    }

    @Override
    public String generateCopy(String prompt) {
        if (groqApiKey == null || groqApiKey.isBlank() || "YOUR_GROQ_API_KEY_HERE".equals(groqApiKey)) {
            log.info("Groq API key not provided. Returning curated copywriting suggestion.");
            return "{\"headline\": \"Transforming Concepts into Top Board & Competitive Ranks\", \"subheading\": \"Personalized guidance, small batch sizes, and weekly test series designed for guaranteed score improvement.\"}";
        }

        Map<String, Object> requestBody = Map.of(
            "model", "GPT OSS 20B",
            "messages", List.of(
                Map.of("role", "system", "content", "You are an expert copywriter for coaching classes. Generate high-trust, conversion-focused headlines and teacher bios."),
                Map.of("role", "user", "content", prompt)
            ),
            "temperature", 0.5,
            "max_tokens", 300
        );

        try {
            return callGroqApi(requestBody);
        } catch (Exception e) {
            log.warn("Groq copy generation failed: {}. Returning fallback copy.", e.getMessage());
            return "{\"headline\": \"Quality Education for Bright Futures\", \"subheading\": \"Join hundreds of successful students achieving excellence with experienced teachers.\"}";
        }
    }

    private String callGroqApi(Map<String, Object> requestBody) {
        return restClient.post()
            .uri(groqApiUrl)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + groqApiKey)
            .contentType(MediaType.APPLICATION_JSON)
            .body(requestBody)
            .retrieve()
            .body(String.class);
    }
}
