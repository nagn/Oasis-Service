package com.turboocelots.oasis.service.models;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by mlin on 4/24/17.
 */
public interface SecurityLogRepository extends CrudRepository<SecurityLog, Long> {
        Optional<SecurityLog> findById(Long id);
}
