package com.quickboard.resourceprofile.profile.repository.querydsl.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.quickboard.resourceprofile.profile.dto.ProfileResponse;
import com.quickboard.resourceprofile.profile.repository.querydsl.ProfileRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static com.quickboard.resourceprofile.profile.entity.QProfile.profile;

@RequiredArgsConstructor
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ProfileResponse> readProfileByProfileId(Long profileId) {
        ProfileResponse result = queryFactory
                .select(Projections
                        .constructor(ProfileResponse.class, profile.id, profile.nickname, profile.firstName, profile.lastName, profile.gender, profile.birthdate))
                .from(profile)
                .where(profile.id.eq(profileId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<ProfileResponse> readProfileByAccountId(Long accountId) {
        ProfileResponse result = queryFactory
                .select(Projections
                        .constructor(ProfileResponse.class, profile.id, profile.nickname, profile.firstName, profile.lastName, profile.gender, profile.birthdate))
                .from(profile)
                .where(profile.accountId.eq(accountId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Page<ProfileResponse> searchProfileByAccountIds(List<Long> profileIds, Pageable pageable) {
        long offset = pageable.getOffset();
        int limit = pageable.getPageSize();

        List<ProfileResponse> results = queryFactory
                .select(Projections
                        .constructor(ProfileResponse.class, profile.id, profile.nickname, profile.firstName, profile.lastName, profile.gender, profile.birthdate))
                .from(profile)
                .where(profile.id.in(profileIds))
                .offset(offset)
                .limit(limit)
                .fetch();

        Long total = queryFactory.select(profile.count())
                .from(profile)
                .where(profile.id.in(profileIds))
                .fetchOne();

        return new PageImpl<>(results, pageable, Objects.nonNull(total) ? total : 0);
    }

    @Override
    public long deleteProfileByAccountId(Long accountId) {
        return queryFactory
                .delete(profile)
                .where(profile.accountId.eq(accountId))
                .execute();
    }

}
