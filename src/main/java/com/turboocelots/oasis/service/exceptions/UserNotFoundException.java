package com.turboocelots.oasis.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mlin on 2/25/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId) {
        super("Could not find user " + userId);
    }
}
