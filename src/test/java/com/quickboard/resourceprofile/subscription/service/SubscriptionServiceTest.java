package com.quickboard.resourceprofile.subscription.service;

import com.quickboard.resourceprofile.profile.entity.Profile;
import com.quickboard.resourceprofile.subscription.dto.SubscriptionRequest;
import com.quickboard.resourceprofile.subscription.entity.Subscription;
import com.quickboard.resourceprofile.subscription.repository.SubscriptionRepository;
import com.quickboard.resourceprofile.subscription.service.impl.SubscriptionServiceImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionServiceTest {

    private SubscriptionService subscriptionService;
    private SubscriptionRepository subscriptionRepository;
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        subscriptionRepository = Mockito.mock(SubscriptionRepository.class);
        entityManager = Mockito.mock(EntityManager.class);
        subscriptionService = new SubscriptionServiceImpl(subscriptionRepository, entityManager);
    }

    @Test
    void searchSubscriptions() {
        Pageable pageable = PageRequest.of(0, 10);
        Long profileId = 10L;
        Mockito.when(subscriptionRepository.searchSubscriptions(profileId, pageable))
                .thenReturn(null);

        subscriptionService.searchSubscriptions(profileId, pageable);

        Mockito.verify(subscriptionRepository, Mockito.times(1))
                .searchSubscriptions(profileId, pageable);
    }

    @Test
    void createSubscription() {
        Long profileId = 10L;
        SubscriptionRequest request = new SubscriptionRequest(3L);
        Mockito.when(entityManager.getReference(Profile.class, profileId)).thenReturn(new Profile());
        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class))).thenReturn(new Subscription());

        subscriptionService.createSubscription(profileId, request);

        Mockito.verify(entityManager, Mockito.times(1))
                .getReference(Profile.class, profileId);
        Mockito.verify(subscriptionRepository, Mockito.times(1))
                .save(Mockito.any(Subscription.class));
    }

    @Test
    void deleteSubscription() {
        Long subscriptionId = 10L;
        Mockito.doNothing().when(subscriptionRepository).deleteById(subscriptionId);

        subscriptionService.deleteSubscription(subscriptionId);

        Mockito.verify(subscriptionRepository, Mockito.times(1))
                .deleteById(subscriptionId);
    }
}