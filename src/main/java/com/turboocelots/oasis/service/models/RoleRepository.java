package com.turboocelots.oasis.service.models;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by mlin on 2/25/17.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
}