package com.meyb.househarmony.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a profile is not found in the system.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProfileNotFoundException extends RuntimeException {
    
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
