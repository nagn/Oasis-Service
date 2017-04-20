package com.turboocelots.oasis.service.models;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by mlin on 4/20/17.
 */
public interface WaterQualityReportsRepository extends CrudRepository<WaterQualityReport, Long> {
    Optional<OasisUser> findById(Long id);
}
