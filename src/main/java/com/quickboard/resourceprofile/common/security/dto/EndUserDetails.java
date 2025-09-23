package com.quickboard.resourceprofile.common.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quickboard.resourceprofile.common.security.enums.AccountState;
import com.quickboard.resourceprofile.common.security.enums.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EndUserDetails(
        Long accountId,
        AccountState accountState,
        Role role,
        Long profileId,
        String nickname
) { }
