package com.quickboard.resourceprofile.profile.service;

import com.quickboard.resourceprofile.profile.dto.ProfileBulkRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileResponse;
import com.quickboard.resourceprofile.profile.entity.Profile;
import com.quickboard.resourceprofile.profile.enums.Gender;
import com.quickboard.resourceprofile.profile.exception.impl.ProfileNotFoundException;
import com.quickboard.resourceprofile.profile.repository.ProfileRepository;
import com.quickboard.resourceprofile.profile.service.impl.ProfileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProfileServiceTest {

    private ProfileService profileService;
    private ProfileRepository profileRepository;

    @BeforeEach
    void setUp() {
        profileRepository = Mockito.mock(ProfileRepository.class);
        profileService = new ProfileServiceImpl(profileRepository);
    }

    @Test
    void searchProfileFail() {
        Long profileId = 10L;
        Mockito.when(profileRepository.readProfileByProfileId(profileId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ProfileNotFoundException.class, () -> profileService.searchProfile(profileId));
    }

    @Test
    void searchProfileSuccess() {
        Long profileId = 10L;
        ProfileResponse response = new ProfileResponse(null, null, null, null, null, null, null, null);
        Mockito.when(profileRepository.readProfileByProfileId(profileId)).thenReturn(Optional.of(response));

        ProfileResponse actual = profileService.searchProfile(profileId);

        Assertions.assertEquals(response, actual);
    }

    @Test
    void searchMyProfileFail() {
        Long accountId = 10L;
        Mockito.when(profileRepository.readProfileByAccountId(accountId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ProfileNotFoundException.class, () -> profileService.searchMyProfile(accountId));
    }

    @Test
    void searchMyProfileSuccess() {
        Long accountId = 10L;
        ProfileResponse response = new ProfileResponse(null, null, null, null, null, null, null, null);
        Mockito.when(profileRepository.readProfileByAccountId(accountId)).thenReturn(Optional.of(response));

        ProfileResponse actual = profileService.searchMyProfile(accountId);

        Assertions.assertEquals(response, actual);
    }

    @Test
    void searchProfilesByIds() {
        List<Long> ids = List.of();
        ProfileBulkRequest request = new ProfileBulkRequest(ids);
        List<ProfileResponse> response = List.of();
        Mockito.when(profileRepository.searchProfileByAccountIds(ids))
                .thenReturn(response);

        List<ProfileResponse> actual = profileService.searchProfilesByIds(request);

        Assertions.assertEquals(response, actual);
    }

    @Test
    void createProfile() {
        Long accountId = 1L;
        ProfileRequest request = new ProfileRequest("wwe", "john", "cena", Gender.MALE, LocalDate.now());
        Mockito.when(profileRepository.save(Mockito.any(Profile.class))).thenReturn(null);

        profileService.createProfile(accountId, request);

        Mockito.verify(profileRepository, Mockito.times(1))
                .save(Mockito.any(Profile.class));
    }

    @Test
    void updateProfile() {
        Long accountId = 1L;
        ProfileRequest request = new ProfileRequest("wwe", "john", "cena", Gender.MALE, LocalDate.now());
        Profile profile = new Profile();
        Mockito.when(profileRepository.findByAccountId(accountId)).thenReturn(Optional.of(profile));

        profileService.updateProfile(accountId, request);

        Assertions.assertAll(
                () -> Assertions.assertEquals(request.nickname(), profile.getNickname()),
                () -> Assertions.assertEquals(request.firstName(), profile.getFirstName()),
                () -> Assertions.assertEquals(request.lastName(), profile.getLastName()),
                () -> Assertions.assertEquals(request.gender(), profile.getGender()),
                () -> Assertions.assertEquals(request.birthdate(), profile.getBirthdate())
        );
    }

    @Test
    void updateProfileException() {
        Long accountId = 1L;
        Mockito.when(profileRepository.findByAccountId(accountId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ProfileNotFoundException.class,
                () -> profileService.updateProfile(accountId, null));
    }

    @Test
    void deleteProfileGt2() {
        Long accountId = 1L;
        Mockito.when(profileRepository.deleteProfileByAccountId(accountId)).thenReturn(2L);

        Assertions.assertThrows(IllegalArgumentException.class, () -> profileService.deleteProfile(accountId));
    }

    @Test
    void deleteProfileEq0() {
        Long accountId = 1L;
        Mockito.when(profileRepository.deleteProfileByAccountId(accountId)).thenReturn(0L);

        Assertions.assertThrows(ProfileNotFoundException.class, () -> profileService.deleteProfile(accountId));
    }

    @Test
    void deleteProfileSuccess() {
        Long accountId = 1L;
        Mockito.when(profileRepository.deleteProfileByAccountId(accountId)).thenReturn(1L);

        profileService.deleteProfile(accountId);

        Mockito.verify(profileRepository, Mockito.times(1))
                .deleteProfileByAccountId(accountId);
    }
}