package com.quickboard.resourceprofile.profile.dto;

import com.quickboard.resourceprofile.profile.enums.Gender;

import java.time.LocalDate;

public record ProfileRequest(
        String nickname,
        String firstName,
        String lastName,
        Gender gender,
        LocalDate birthdate
) { }
