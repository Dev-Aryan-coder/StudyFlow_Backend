package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.ChangePasswordRequest;
import com.example.StudyFlow.dto.request.ProfileUpdateRequest;
import com.example.StudyFlow.dto.response.UserResponse;

public interface UserService {

    UserResponse getProfile(String userId);

    UserResponse updateProfile(String userId, ProfileUpdateRequest request);

    void changePassword(String userId, ChangePasswordRequest request);
}
