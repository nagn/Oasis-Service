package com.turboocelots.oasis.service.controllers;

import com.turboocelots.oasis.service.exceptions.*;
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
        this.validateUserID(userId);
        OasisUser user = this.userRepository.findById(userId).get();
        // Then delete.
        this.userRepository.delete(user);
        return "Success!";
    }

    @RequestMapping(value="/api/user/create", method = RequestMethod.POST)
    OasisUser createUser (@RequestBody OasisUser input) {
        this.checkIfNewUserName(input.getUserName());
        this.validateUserType(input.getUserType());
        OasisUser user = new OasisUser(input.getUserName(),input.getPassword(), input.getUserType());
        this.userRepository.save(user);
        return user;
    }

    @RequestMapping(value= "/api/user/login", method = RequestMethod.POST)
    Long loginUser (@RequestBody Map<String, String> payload) {
        if (!payload.containsKey("username") || !payload.containsKey("password")) {
            throw new InvalidLogin();
        }
        validateUsernameAndPassword(payload.get("username"), payload.get("password"));
        OasisUser user = this.userRepository.findByUserName(payload.get("username")).get();
        return user.getId();
    }

    @RequestMapping(value="/api/user/{userId}", method = RequestMethod.PUT)
    OasisUser updateUser (@PathVariable Long userId,  @RequestBody OasisUser input) {
        this.validateUserID(userId);
        OasisUser user = this.userRepository.findById(userId).get();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setHomeAddress(input.getHomeAddress());
        user.setTitle(input.getTitle());
        user.setPhoneNumber(input.getPhoneNumber());
        return user;
    }

    @RequestMapping(value="/api/user/{userId}", method = RequestMethod.GET)
    OasisUser getUser (@PathVariable Long userId) {
        this.validateUserID(userId);
        OasisUser user = this.userRepository.findById(userId).get();
        return user;
    }

    @RequestMapping(value="/api/users", method = RequestMethod.GET)
    Collection<OasisUser> getUsers () {
        List<OasisUser> list = new ArrayList<OasisUser>();
        this.userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    private void checkIfNewUserName(String userName) {
        this.userRepository.findByUserName(userName).ifPresent(x -> {
            throw new UserNameAlreadyExists(userName);
        });
    }

    private void validateUsernameAndPassword(String userName, String password) {
        OasisUser user = this.userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));
        if (!user.getPassword().equals(password)) {
            throw new InvalidPassword(password);
        }
    }

    private void validateUserType(String userType) {
        if (userType == null) throw new InvalidUserType();
        if (!userType.equals("Administrator") && !userType.equals("Worker") && !userType.equals("Reporter") && !userType.equals("Manager")) {
            throw new InvalidUserType(userType);
        }
    }

    private void validateUserID(Long userId) {
        this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
