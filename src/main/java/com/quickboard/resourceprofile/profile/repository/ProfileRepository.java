package com.quickboard.resourceprofile.profile.repository;

import com.quickboard.resourceprofile.profile.entity.Profile;
import com.quickboard.resourceprofile.profile.repository.querydsl.ProfileRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom {
    Optional<Profile> findByAccountId(Long accountId);
}
