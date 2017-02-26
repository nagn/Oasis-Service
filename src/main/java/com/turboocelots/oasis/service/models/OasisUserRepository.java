package com.turboocelots.oasis.service.models;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


/**
 * Created by mlin on 2/25/17.
 */
public interface OasisUserRepository extends CrudRepository<OasisUser, Long> {

    Optional<OasisUser> findByUserName(String userName);
}