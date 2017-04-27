package com.turboocelots.oasis.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mlin on 4/20/17.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserBlocked extends RuntimeException {
    public UserBlocked(String userName) {
        super(userName + " account is currently blocked. Please contact an administrator to unblock your account.");
    }
}