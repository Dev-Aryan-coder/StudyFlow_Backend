package com.studyflow.controller;

import com.studyflow.dto.request.PamphletScanRequest;
import com.studyflow.dto.response.ApiResponse;
import com.studyflow.service.GroqAiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final GroqAiService groqAiService;

    /**
     * Multimodal Pamphlet Extraction using Groq Qwen 3.8 27B Vision
     */
    @PostMapping("/scan-pamphlet")
    public ResponseEntity<ApiResponse<String>> scanPamphlet(@Valid @RequestBody PamphletScanRequest request) {
        String jsonResult = groqAiService.extractPamphletToJson(request.getBase64Image());
        return ResponseEntity.ok(ApiResponse.success(jsonResult, "Pamphlet extracted successfully"));
    }

    /**
     * Sub-second Marketing Copy & Bio Generator using Groq GPT OSS 20B
     */
    @PostMapping("/generate-copy")
    public ResponseEntity<ApiResponse<String>> generateCopy(@RequestBody Map<String, String> request) {
        String prompt = request.getOrDefault("prompt", "Generate a tagline for 10th and 12th board coaching");
        String copyResult = groqAiService.generateCopy(prompt);
        return ResponseEntity.ok(ApiResponse.success(copyResult, "Copy generated successfully"));
    }
}
