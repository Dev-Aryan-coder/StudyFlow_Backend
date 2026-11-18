package com.example.StudyFlow.service;

public interface GroqAiService {

    String extractPamphletToJson(String base64Image);

    String generateCopy(String prompt);
}

