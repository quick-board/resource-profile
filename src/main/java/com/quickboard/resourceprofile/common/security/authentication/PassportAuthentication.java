package com.quickboard.resourceprofile.common.security.authentication;

import com.quickboard.resourceprofile.common.security.dto.Passport;
import com.quickboard.resourceprofile.common.security.enums.CallerType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class PassportAuthentication implements Authentication {

    private final Passport passport;
    private boolean authenticated;
    private final List<GrantedAuthority> authorities;

    private PassportAuthentication(Passport passport, boolean authenticated) {
        this.passport = passport;
        this.authenticated = authenticated;

        String authorityRole;
        if(passport.callerType() == CallerType.SERVICE){
            authorityRole = passport.callerType().name();
        }
        else if (passport.callerType() == CallerType.END_USER) {
            authorityRole = passport.endUserDetails().role().name();
        } else {
            authorityRole = CallerType.ANONYMOUS.name();
        }

        authorities = List.of(new SimpleGrantedAuthority("ROLE_" + authorityRole));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        switch (passport.callerType()){
            case SERVICE -> {
                return passport.serviceDetails();
            }
            case END_USER -> {
                return passport.endUserDetails();
            }
            case ANONYMOUS -> {
                return passport.anonymousDetails();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public Object getPrincipal() {
        return passport;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        switch (passport.callerType()){
            case SERVICE -> {
                return "service:" + passport.serviceDetails().serviceName();
            }
            case END_USER -> {
                return "end user:" + passport.endUserDetails().accountId();
            }
            case ANONYMOUS -> {
                return "anonymous";
            }
            default -> {
                return null;
            }
        }
    }

    public static PassportAuthentication authenticated(Passport passport){
        return new PassportAuthentication(passport, true);
    }

}
