package com.turboocelots.oasis.service.controllers;

import com.turboocelots.oasis.service.exceptions.UserAlreadyExists;
import com.turboocelots.oasis.service.exceptions.UserNameAlreadyExists;
import com.turboocelots.oasis.service.exceptions.UserNotFoundException;
import com.turboocelots.oasis.service.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Created by mlin on 2/25/17.
 */
@RestController
public class OasisUserController {
    private final OasisUserRepository userRepository;

    @Autowired
    OasisUserController(OasisUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value= "/api/user/{userId}", method = RequestMethod.DELETE)
    String deleteUser (@PathVariable Long userId) {
        this.validateUser(userId);
        OasisUser user = this.userRepository.findById(userId).get();
        // Then delete.
        this.userRepository.delete(user);
        return "Success!";
    }

    @RequestMapping(value="/api/user/create", method = RequestMethod.POST)
    OasisUser createUser (@RequestBody OasisUser input) {
        this.checkIfNew(input.getUserName());
        OasisUser user = new OasisUser(input.getUserName(),input.getPassword(), input.getUserType());
        this.userRepository.save(user);
        return user;
    }

    @RequestMapping(value="/api/user/{userId}", method = RequestMethod.PUT)
    OasisUser updateUser (@PathVariable Long userId,  @RequestBody OasisUser input) {
        this.validateUser(userId);
        OasisUser user = this.userRepository.findById(userId).get();
        user.setFullName(input.getFullName());
        user.setUserType(input.getUserType());
        return user;
    }

    @RequestMapping(value="/api/user/{userId}", method = RequestMethod.GET)
    OasisUser getUser (@PathVariable Long userId) {
        this.validateUser(userId);
        OasisUser user = this.userRepository.findById(userId).get();
        return user;
    }

    @RequestMapping(value="/api/users", method = RequestMethod.GET)
    Collection<OasisUser> getUsers () {
        List<OasisUser> list = new ArrayList<OasisUser>();
        this.userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    private void checkIfNew(String userName) {
        this.userRepository.findByUserName(userName).ifPresent(x -> {
            throw new UserNameAlreadyExists(userName);
        });
    }

    private void validateUser(Long userId) {
        this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
