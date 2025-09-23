package com.quickboard.resourceprofile.common.security.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AccountState {
    ACTIVE,
    INACTIVE,
    SLEEPING;

    @JsonCreator
    public static AccountState from(String state){
        try {
            return AccountState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid argument: " + state);
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
