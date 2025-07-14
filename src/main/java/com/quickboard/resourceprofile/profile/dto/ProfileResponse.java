package com.quickboard.resourceprofile.profile.dto;

import com.quickboard.resourceprofile.profile.enums.Gender;

import java.time.LocalDate;

public record ProfileResponse(
        Long id,
        String nickname,
        String firstName,
        String lastName,
        Gender gender,
        LocalDate birthdate
) {}
