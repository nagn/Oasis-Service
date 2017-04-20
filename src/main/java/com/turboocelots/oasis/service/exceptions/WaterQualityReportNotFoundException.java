package com.turboocelots.oasis.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mlin on 2/25/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class WaterQualityReportNotFoundException extends RuntimeException{
    public WaterQualityReportNotFoundException(Long reportId) {
        super("Could not find water quality report: " + reportId);
    }
}
