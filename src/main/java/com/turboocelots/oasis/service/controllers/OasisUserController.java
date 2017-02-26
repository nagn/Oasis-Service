package com.turboocelots.oasis.service.controllers;

import com.turboocelots.oasis.service.models.OasisUser;
import com.turboocelots.oasis.service.models.OasisUserRepository;
import com.turboocelots.oasis.service.models.RoleRepository;
import com.turboocelots.oasis.service.models.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


/**
 * Created by mlin on 2/25/17.
 */
@RestController
@RequestMapping("/api/{userId}")
public class OasisUserController {
    private final OasisUserRepository userRepository;

    @Autowired
    OasisUserController(OasisUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    OasisUser getUser (@PathVariable String userId) {
        this.validateUser(userId);
        return this.userRepository.findByUserName(userId).get();
    }

    @RequestMapping(method = RequestMethod.POST)
    OasisUser createUser (@PathVariable String userId) {
        OasisUser user = this.userRepository.findOne(Long.parseLong(userId));
        user.setUserName("test");
        this.userRepository.save(user);
        return user;
    }
    private void validateUser(String userId) {
        this.userRepository
                .findByUserName(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
