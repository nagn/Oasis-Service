package com.turboocelots.oasis.service.controllers;

import com.turboocelots.oasis.service.exceptions.InvalidRoleName;
import com.turboocelots.oasis.service.exceptions.NoPermissions;
import com.turboocelots.oasis.service.exceptions.UserAlreadyExists;
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
    private final RoleRepository roleRepository;

    @Autowired
    OasisUserController(OasisUserRepository userRepository,
                        RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value= "/api/user/{userId}", method = RequestMethod.DELETE)
    String deleteUser (@PathVariable String userId) {
        this.validateUser(userId);
        OasisUser user = this.userRepository.findByUserName(userId).get();
        for (Role role: user.getRoles()) {
            // We have to remove the corresponding user reference from the database
            role.removeOasisUsers(Arrays.asList(user));
        }
        this.roleRepository.save(user.getRoles());
        // Delete all roles from user
        user.removeRoles(user.getRoles());
        this.userRepository.save(user);
        // Then delete.
        this.userRepository.delete(user);
        return "Success!";
    }

    @RequestMapping(value="/api/user/create", method = RequestMethod.POST)
    OasisUser createUser (@RequestBody OasisUser input) {
        this.checkIfNew(input.getUserName());
        Set<Role> roles = this.validateRolesList(input.getPermissions());
        OasisUser user = new OasisUser(input.getUserName(),input.getPassword(), input.getPermissions());
        user.addRoles(roles);
        this.userRepository.save(user);
        return user;
    }

    @RequestMapping(value="/api/user/{userId}", method = RequestMethod.PUT)
    OasisUser updateUser (@PathVariable String userId,  @RequestBody OasisUser input) {
        this.validateUser(userId);
        OasisUser user = this.userRepository.findByUserName(userId).get();
        return user;
    }

    @RequestMapping(value="/api/user/{userId}", method = RequestMethod.GET)
    OasisUser getUser (@PathVariable String userId) {
        this.validateUser(userId);
        OasisUser user = this.userRepository.findByUserName(userId).get();
        return user;
    }

    @RequestMapping(value="/api/users", method = RequestMethod.GET)
    Collection<OasisUser> getUsers () {
        List<OasisUser> list = new ArrayList<OasisUser>();
        this.userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    private void checkIfNew(String userId) {
        this.userRepository.findByUserName(userId).ifPresent(x -> {
            throw new UserAlreadyExists(userId);
        });
    }

    private Set<Role> validateRolesList(ArrayList<String> permissions) {
        if (permissions == null || (permissions.size() == 0)) throw new NoPermissions();
        Set<Role> roleSet = new HashSet<>();
                permissions
                .stream()
                .forEach((String roleName) ->
                    this.roleRepository.findByName(roleName)
                        .map(roleSet::add)
                        .orElseThrow(() -> new InvalidRoleName(roleName)));
        return roleSet;
    }

    private void validateUser(String userId) {
        this.userRepository
                .findByUserName(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
