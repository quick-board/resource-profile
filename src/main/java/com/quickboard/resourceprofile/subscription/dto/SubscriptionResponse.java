package com.quickboard.resourceprofile.subscription.dto;

import java.time.Instant;

public record SubscriptionResponse(
    Long id,
    Long boardId,
    Instant createdAt,
    Instant updatedAt
) { }
