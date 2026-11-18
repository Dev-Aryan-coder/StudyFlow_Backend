package com.example.StudyFlow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicLoginResponse {

    private String token;
    private String userId;
    private String name;
    private String role;
    private String studentId;
    private String email;
    private String instituteName;
    private String redirectUrl;
}
