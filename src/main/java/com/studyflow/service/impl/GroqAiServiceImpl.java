package com.studyflow.service.impl;

import com.studyflow.service.GroqAiService;
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

    @Value("${groq.api.key}")
    private String groqApiKey;

    @Value("${groq.api.url:https://api.groq.com/openai/v1/chat/completions}")
    private String groqApiUrl;

    private final RestClient restClient = RestClient.create();

    @Override
    public String extractPamphletToJson(String base64Image) {
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

        return callGroqApi(requestBody);
    }

    @Override
    public String generateCopy(String prompt) {
        Map<String, Object> requestBody = Map.of(
            "model", "GPT OSS 20B",
            "messages", List.of(
                Map.of("role", "system", "content", "You are an expert copywriter for coaching classes. Generate high-trust, conversion-focused headlines and teacher bios."),
                Map.of("role", "user", "content", prompt)
            ),
            "temperature", 0.5,
            "max_tokens", 300
        );

        return callGroqApi(requestBody);
    }

    private String callGroqApi(Map<String, Object> requestBody) {
        try {
            return restClient.post()
                .uri(groqApiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + groqApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(String.class);
        } catch (Exception ex) {
            log.error("Error communicating with Groq API: {}", ex.getMessage());
            throw new RuntimeException("Groq API communication failed: " + ex.getMessage());
        }
    }
}
