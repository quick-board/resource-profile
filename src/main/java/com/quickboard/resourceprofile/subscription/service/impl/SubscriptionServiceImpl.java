package com.quickboard.resourceprofile.subscription.service.impl;

import com.quickboard.resourceprofile.profile.entity.Profile;
import com.quickboard.resourceprofile.profile.exception.impl.ProfileNotFoundException;
import com.quickboard.resourceprofile.subscription.dto.SubscriptionRequest;
import com.quickboard.resourceprofile.subscription.dto.SubscriptionResponse;
import com.quickboard.resourceprofile.subscription.entity.Subscription;
import com.quickboard.resourceprofile.subscription.repository.SubscriptionRepository;
import com.quickboard.resourceprofile.subscription.service.SubscriptionService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Page<SubscriptionResponse> searchSubscriptions(Long profileId, Pageable pageable) {
        return subscriptionRepository.searchSubscriptions(profileId, pageable);
    }

    @Transactional
    @Override
    public void createSubscription(Long profileId, SubscriptionRequest subscriptionRequest) {
        Profile profile = entityManager.getReference(Profile.class, profileId);
        Subscription newObject = Subscription.builder()
                .profile(profile)
                .boardId(subscriptionRequest.boardId())
                .build();

        subscriptionRepository.save(newObject);

    }

    @Transactional
    @Override
    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }
}
