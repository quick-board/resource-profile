package com.quickboard.resourceprofile.profile.controller;

import com.quickboard.resourceprofile.profile.dto.ProfileBulkRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileResponse;
import com.quickboard.resourceprofile.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rsc/v1")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profiles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse getProfile(@PathVariable("id") Long profileId){
        return profileService.searchProfile(profileId);
    }

    @GetMapping("/profiles/me")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse getMyProfile(@RequestHeader("x-account-id") Long accountId){
        return profileService.searchMyProfile(accountId);
    }

    //todo pageable 필요없음~~
    @PostMapping("/profiles/bulk")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProfileResponse> getProfilesByIds(@RequestBody ProfileBulkRequest profileBulkRequest,
                                                  @ParameterObject Pageable pageable){
        return profileService.searchProfilesByIds(profileBulkRequest, pageable);
    }

    @PostMapping("/profiles")
    @ResponseStatus(HttpStatus.CREATED)
    public void postProfile(@RequestHeader("x-account-id") Long accountId,
                            @RequestBody ProfileRequest profileRequest){
        profileService.createProfile(accountId, profileRequest);
    }

    @PatchMapping("/profiles/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchProfile(@RequestHeader("x-account-id") Long accountId,
                             @RequestBody ProfileRequest profileRequest){
        profileService.updateProfile(accountId, profileRequest);
    }

    @DeleteMapping("/profiles/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@RequestHeader("x-account-id") Long accountId){
        profileService.deleteProfile(accountId);
    }

}
