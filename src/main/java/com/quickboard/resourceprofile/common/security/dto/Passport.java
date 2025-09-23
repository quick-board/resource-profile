package com.quickboard.resourceprofile.common.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quickboard.resourceprofile.common.security.enums.AccountState;
import com.quickboard.resourceprofile.common.security.enums.CallerType;
import com.quickboard.resourceprofile.common.security.enums.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Passport(
        CallerType callerType,
        AnonymousDetails anonymousDetails,
        EndUserDetails endUserDetails,
        ServiceDetails serviceDetails
) {
    public static Passport anonymousPassport(String guestId, String ip){
        return new Passport(
                CallerType.ANONYMOUS,
                new AnonymousDetails(guestId, ip),
                null,
                null
        );
    }

    public static Passport endUserPassport(Long accountId, AccountState accountState, Role role, Long profileId, String nickname){
        return new Passport(
                CallerType.END_USER,
                null,
                new EndUserDetails(accountId, accountState, role, profileId, nickname),
                null
        );
    }

    public static Passport servicePassport(String serviceName, String clientRequestPath){
        return new Passport(
                CallerType.SERVICE,
                null,
                null,
                new ServiceDetails(serviceName, clientRequestPath)
        );
    }

}
