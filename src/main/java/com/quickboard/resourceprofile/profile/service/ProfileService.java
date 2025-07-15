package com.quickboard.resourceprofile.profile.service;

import com.quickboard.resourceprofile.profile.dto.ProfileBulkRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileResponse;

import java.util.List;

public interface ProfileService {
    ProfileResponse searchProfile(Long profileId);
    ProfileResponse searchMyProfile(Long accountId);
    List<ProfileResponse> searchProfilesByIds(ProfileBulkRequest profileBulkRequest);
    void createProfile(Long accountId, ProfileRequest profileRequest);
    void updateProfile(Long accountId, ProfileRequest profileRequest);
    void deleteProfile(Long accountId);
}
