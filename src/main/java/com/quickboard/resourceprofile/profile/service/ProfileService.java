package com.quickboard.resourceprofile.profile.service;

import com.quickboard.resourceprofile.profile.dto.ProfileBulkRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService {
    ProfileResponse searchProfile(Long profileId);
    ProfileResponse searchMyProfile(Long accountId);
    Page<ProfileResponse> searchProfilesByIds(ProfileBulkRequest profileBulkRequest, Pageable pageable);
    void createProfile(Long accountId, ProfileRequest profileRequest);
    void updateProfile(Long accountId, ProfileRequest profileRequest);
    void deleteProfile(Long accountId);
}
