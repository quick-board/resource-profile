package com.quickboard.resourceprofile.subscription.repository;

import com.quickboard.resourceprofile.subscription.entity.Subscription;
import com.quickboard.resourceprofile.subscription.repository.querydsl.SubscriptionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>, SubscriptionRepositoryCustom {
}
