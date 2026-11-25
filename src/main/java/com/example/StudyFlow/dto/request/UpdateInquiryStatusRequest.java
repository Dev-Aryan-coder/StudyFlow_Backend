package com.example.StudyFlow.dto.request;

import com.example.StudyFlow.enums.InquiryStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInquiryStatusRequest {

    @NotNull(message = "Inquiry status is required")
    private InquiryStatus status;
}
