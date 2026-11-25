package com.example.StudyFlow.dto.request;

import com.example.StudyFlow.enums.FeatureRequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFeatureStatusRequest {

    @NotNull(message = "Feature request status is required")
    private FeatureRequestStatus status;
}
