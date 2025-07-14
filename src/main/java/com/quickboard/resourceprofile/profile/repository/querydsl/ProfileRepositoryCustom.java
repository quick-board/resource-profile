package com.quickboard.resourceprofile.profile.repository.querydsl;

import com.quickboard.resourceprofile.profile.dto.ProfileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProfileRepositoryCustom {
    Optional<ProfileResponse> readProfileByProfileId(Long profileId);
    Optional<ProfileResponse> readProfileByAccountId(Long accountId);
    Page<ProfileResponse> searchProfileByAccountIds(List<Long> profileIds, Pageable pageable);
    long deleteProfileByAccountId(Long accountId);
}
