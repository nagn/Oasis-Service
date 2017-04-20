package com.turboocelots.oasis.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mlin on 2/25/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class WaterSourceReportNotFoundException extends RuntimeException{
    public WaterSourceReportNotFoundException(Long reportId) {
        super("Could not find water source report: " + reportId);
    }
}
