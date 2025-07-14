package com.quickboard.resourceprofile.profile.exception.impl;

import com.quickboard.resourceprofile.common.exception.ResourceNotFoundException;

public class ProfileNotFoundException extends ResourceNotFoundException {
    public ProfileNotFoundException() {
    }

    public ProfileNotFoundException(Throwable cause) {
        super(cause);
    }
}
