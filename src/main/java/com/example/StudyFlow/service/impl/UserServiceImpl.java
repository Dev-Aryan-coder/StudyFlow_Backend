package com.example.StudyFlow.service.impl;

import com.example.StudyFlow.dto.request.ChangePasswordRequest;
import com.example.StudyFlow.dto.request.ProfileUpdateRequest;
import com.example.StudyFlow.dto.response.UserResponse;
import com.example.StudyFlow.entity.User;
import com.example.StudyFlow.exception.BadRequestException;
import com.example.StudyFlow.exception.ResourceNotFoundException;
import com.example.StudyFlow.repository.UserRepository;
import com.example.StudyFlow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return mapToUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateProfile(String userId, ProfileUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // If username is changed, verify uniqueness
        if (!user.getUsername().equalsIgnoreCase(request.getUsername()) &&
                userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username is already taken: " + request.getUsername());
        }

        user.setUsername(request.getUsername().trim());
        user.setPhoneNumber(request.getPhoneNumber().trim());
        user.setClassName(request.getClassName().trim());

        User updatedUser = userRepository.save(user);
        return mapToUserResponse(updatedUser);
    }

    @Override
    @Transactional
    public void changePassword(String userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Current password does not match");
        }

        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .className(user.getClassName())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
