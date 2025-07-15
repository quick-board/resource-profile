package com.quickboard.resourceprofile.subscription.repository.querydsl;


import com.quickboard.resourceprofile.subscription.dto.SubscriptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionRepositoryCustom {
    Page<SubscriptionResponse> searchSubscriptions(Long profileId, Pageable pageable);
}
