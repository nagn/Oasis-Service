package com.turboocelots.oasis.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mlin on 2/26/17.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserType extends RuntimeException {
    public InvalidUserType(String userType) {
        super("UserType is invalid: " + userType);
    }
}