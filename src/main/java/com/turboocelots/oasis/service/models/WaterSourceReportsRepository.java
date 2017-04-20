package com.turboocelots.oasis.service.models;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by mlin on 4/20/17.
 */
public interface WaterSourceReportsRepository extends CrudRepository<WaterSourceReport, Long> {
    Optional<WaterSourceReport> findById(Long id);
}
