package com.quickboard.resourceprofile.subscription.repository.querydsl.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.quickboard.resourceprofile.subscription.dto.SubscriptionResponse;
import com.quickboard.resourceprofile.subscription.repository.querydsl.SubscriptionRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

import static com.quickboard.resourceprofile.subscription.entity.QSubscription.subscription;

@RequiredArgsConstructor
public class SubscriptionRepositoryCustomImpl implements SubscriptionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SubscriptionResponse> searchSubscriptions(Long profileId, Pageable pageable) {

        long offset = pageable.getOffset();
        int limit = pageable.getPageSize();

        List<SubscriptionResponse> result = queryFactory
                .select(Projections
                        .constructor(SubscriptionResponse.class, subscription.id, subscription.boardId, subscription.createdAt, subscription.updatedAt))
                .from(subscription)
                .where(subscription.profile.id.eq(profileId))
                .offset(offset)
                .limit(limit)
                .fetch();

        Long total = queryFactory
                .select(subscription.count())
                .from(subscription)
                .where(subscription.profile.id.eq(profileId))
                .fetchOne();

        return new PageImpl<>(result, pageable, Objects.nonNull(total) ? total : 0);
    }
}
