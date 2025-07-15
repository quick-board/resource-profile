package com.quickboard.resourceprofile.profile.repository.querydsl;

import com.quickboard.resourceprofile.profile.dto.ProfileResponse;

import java.util.List;
import java.util.Optional;

public interface ProfileRepositoryCustom {
    Optional<ProfileResponse> readProfileByProfileId(Long profileId);
    Optional<ProfileResponse> readProfileByAccountId(Long accountId);
    List<ProfileResponse> searchProfileByAccountIds(List<Long> profileIds);
    long deleteProfileByAccountId(Long accountId);
}
