package com.quickboard.resourceprofile.profile.dto;

import java.util.List;

public record ProfileBulkRequest(
        List<Long> profileIds
) { }
