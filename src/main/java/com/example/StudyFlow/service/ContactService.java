package com.example.StudyFlow.service;

import com.example.StudyFlow.dto.request.ContactInquiryRequest;
import com.example.StudyFlow.dto.request.CustomFeatureRequestDto;
import com.example.StudyFlow.entity.ContactInquiry;
import com.example.StudyFlow.entity.CustomFeatureRequest;
import com.example.StudyFlow.enums.FeatureRequestStatus;
import com.example.StudyFlow.enums.InquiryStatus;

import java.util.List;

public interface ContactService {

    ContactInquiry createInquiry(ContactInquiryRequest request);

    List<ContactInquiry> getAllInquiries();

    ContactInquiry getInquiryById(Long id);

    ContactInquiry updateInquiryStatus(Long id, InquiryStatus status);

    CustomFeatureRequest createCustomFeatureRequest(CustomFeatureRequestDto request);

    List<CustomFeatureRequest> getAllCustomFeatureRequests();

    CustomFeatureRequest getCustomFeatureRequestById(Long id);

    CustomFeatureRequest updateFeatureRequestStatus(Long id, FeatureRequestStatus status);
}
