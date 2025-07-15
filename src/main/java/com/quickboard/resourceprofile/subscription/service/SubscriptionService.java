package com.quickboard.resourceprofile.subscription.service;

import com.quickboard.resourceprofile.subscription.dto.SubscriptionRequest;
import com.quickboard.resourceprofile.subscription.dto.SubscriptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionService {
    Page<SubscriptionResponse> searchSubscriptions(Long profileId, Pageable pageable);
    void createSubscription(Long profileId, SubscriptionRequest subscriptionRequest);
    void deleteSubscription(Long subscriptionId);
}
