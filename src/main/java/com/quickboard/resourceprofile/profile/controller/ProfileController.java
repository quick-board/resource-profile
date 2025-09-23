package com.quickboard.resourceprofile.profile.controller;

import com.quickboard.resourceprofile.common.security.dto.Passport;
import com.quickboard.resourceprofile.profile.dto.ProfileBulkRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileRequest;
import com.quickboard.resourceprofile.profile.dto.ProfileResponse;
import com.quickboard.resourceprofile.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ProfileResponse getMyProfile(Passport passport){
        return profileService.searchProfileByAccountId(passport.endUserDetails().accountId());
    }

    @GetMapping("/profiles/profile")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse getProfileByAccountId(@RequestParam("account-id") Long accountId){
        return profileService.searchProfileByAccountId(accountId);
    }

    @PostMapping("/profiles/bulk")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileResponse> getProfilesByIds(@RequestBody ProfileBulkRequest profileBulkRequest){
        return profileService.searchProfilesByIds(profileBulkRequest);
    }

    @PostMapping("/profiles")
    @ResponseStatus(HttpStatus.CREATED)
    public void postProfile(Passport passport,
                            @RequestBody ProfileRequest profileRequest){
        profileService.createProfile(passport.endUserDetails().accountId(), profileRequest);
    }

    @PostMapping("/profiles/inner")
    @ResponseStatus(HttpStatus.CREATED)
    public void postProfileInner(@RequestHeader("x-account-id") Long accountId, @RequestBody ProfileRequest profileRequest){
        profileService.createProfile(accountId, profileRequest);
    }

    @PatchMapping("/profiles/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchProfile(Passport passport,
                             @RequestBody ProfileRequest profileRequest){
        profileService.updateProfile(passport.endUserDetails().accountId(), profileRequest);
    }

    @DeleteMapping("/profiles/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(Passport passport){
        profileService.deleteProfile(passport.endUserDetails().accountId());
    }

    @GetMapping("/profiles/profile/exists")
    @ResponseStatus(HttpStatus.OK)
    public boolean nicknameExists(@RequestParam("nickname") String nickname){
        return profileService.nicknameExists(nickname);
    }

}
