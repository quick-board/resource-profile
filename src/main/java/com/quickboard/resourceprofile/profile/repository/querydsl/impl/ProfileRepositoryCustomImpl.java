package com.quickboard.resourceprofile.profile.repository.querydsl.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.quickboard.resourceprofile.profile.dto.ProfileResponse;
import com.quickboard.resourceprofile.profile.repository.querydsl.ProfileRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import static com.quickboard.resourceprofile.profile.entity.QProfile.profile;

@RequiredArgsConstructor
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ProfileResponse> readProfileByProfileId(Long profileId) {
        ProfileResponse result = queryFactory
                .select(Projections
                        .constructor(ProfileResponse.class, profile.id, profile.nickname, profile.firstName, profile.lastName, profile.gender, profile.birthdate, profile.createdAt, profile.updatedAt))
                .from(profile)
                .where(profile.id.eq(profileId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<ProfileResponse> readProfileByAccountId(Long accountId) {
        ProfileResponse result = queryFactory
                .select(Projections
                        .constructor(ProfileResponse.class, profile.id, profile.nickname, profile.firstName, profile.lastName, profile.gender, profile.birthdate, profile.createdAt, profile.updatedAt))
                .from(profile)
                .where(profile.accountId.eq(accountId))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public List<ProfileResponse> searchProfileByAccountIds(List<Long> profileIds) {
        return queryFactory
                .select(Projections
                        .constructor(ProfileResponse.class, profile.id, profile.nickname, profile.firstName, profile.lastName, profile.gender, profile.birthdate, profile.createdAt, profile.updatedAt))
                .from(profile)
                .where(profile.id.in(profileIds))
                .fetch();
    }


    @Override
    public long deleteProfileByAccountId(Long accountId) {
        return queryFactory
                .delete(profile)
                .where(profile.accountId.eq(accountId))
                .execute();
    }

}
