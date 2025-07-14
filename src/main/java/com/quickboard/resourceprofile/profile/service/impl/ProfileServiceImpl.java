package com.quickboard.resourceprofile.profile.service.impl;

import com.quickboard.resourceprofile.profile.dto.ProfileBulkRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileResponse;
import com.quickboard.resourceprofile.profile.entity.Profile;
import com.quickboard.resourceprofile.profile.exception.impl.ProfileNotFoundException;
import com.quickboard.resourceprofile.profile.repository.ProfileRepository;
import com.quickboard.resourceprofile.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    @Override
    public ProfileResponse searchProfile(Long profileId) {
        return profileRepository.readProfileByProfileId(profileId).orElseThrow(ProfileNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileResponse searchMyProfile(Long accountId) {
        return profileRepository.readProfileByAccountId(accountId).orElseThrow(ProfileNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProfileResponse> searchProfilesByIds(ProfileBulkRequest profileBulkRequest, Pageable pageable) {
        return profileRepository.searchProfileByAccountIds(profileBulkRequest.profileIds(), pageable);
    }

    @Transactional
    @Override
    public void createProfile(Long accountId, ProfileRequest profileRequest) {
        Profile newProfile = Profile.builder()
                .nickname(profileRequest.nickname())
                .firstName(profileRequest.firstName())
                .lastName(profileRequest.lastName())
                .gender(profileRequest.gender())
                .birthdate(profileRequest.birthdate())
                .accountId(accountId)
                .build();

        profileRepository.save(newProfile);
    }

    @Transactional
    @Override
    public void updateProfile(Long accountId, ProfileRequest profileRequest) {
        Profile profile = profileRepository.findByAccountId(accountId).orElseThrow(ProfileNotFoundException::new);

        profile.setNickname(profileRequest.nickname());
        profile.setFirstName(profileRequest.firstName());
        profile.setLastName(profileRequest.lastName());
        profile.setGender(profileRequest.gender());
        profile.setBirthdate(profileRequest.birthdate());
    }

    @Transactional
    @Override
    public void deleteProfile(Long accountId) {
        long result = profileRepository.deleteProfileByAccountId(accountId);

        if(result >= 2){
            throw new IllegalArgumentException();
        } else if (result == 0) {
            throw new ProfileNotFoundException();
        }
    }
}
