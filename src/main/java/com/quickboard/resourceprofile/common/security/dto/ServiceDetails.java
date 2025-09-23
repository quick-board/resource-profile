package com.quickboard.resourceprofile.common.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ServiceDetails(
    String serviceName,
    String clientRequestPath
) { }
