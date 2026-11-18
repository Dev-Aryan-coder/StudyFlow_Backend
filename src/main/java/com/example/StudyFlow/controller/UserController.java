package com.example.StudyFlow.controller;

import com.example.StudyFlow.dto.request.ChangePasswordRequest;
import com.example.StudyFlow.dto.request.ProfileUpdateRequest;
import com.example.StudyFlow.dto.response.ApiResponse;
import com.example.StudyFlow.dto.response.UserResponse;
import com.example.StudyFlow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * View Profile (username, email, phone, class name)
     */
    @GetMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getProfile(@PathVariable String userId) {
        UserResponse response = userService.getProfile(userId);
        return ResponseEntity.ok(ApiResponse.success(response, "Profile retrieved successfully"));
    }

    /**
     * Update Profile (username, phone, class name)
     */
    @PutMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> updateProfile(
            @PathVariable String userId,
            @Valid @RequestBody ProfileUpdateRequest request) {
        UserResponse response = userService.updateProfile(userId, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Profile updated successfully"));
    }

    /**
     * Change Password from inside Profile
     */
    @PutMapping("/change-password/{userId}")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @PathVariable String userId,
            @Valid @RequestBody ChangePasswordRequest request) {
        userService.changePassword(userId, request);
        return ResponseEntity.ok(ApiResponse.success(null, "Password changed successfully"));
    }
}
