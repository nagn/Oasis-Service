package com.turboocelots.oasis.service.controllers;

import com.turboocelots.oasis.service.exceptions.WaterQualityReportNotFoundException;
import com.turboocelots.oasis.service.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mlin on 4/20/17.
 */
@RestController
public class WaterSourceReportsController {
    private final WaterSourceReportsRepository reportsRepository;

    @Autowired
    WaterSourceReportsController(WaterSourceReportsRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    @RequestMapping(value="/api/sourcereports", method = RequestMethod.GET)
    Collection<WaterSourceReport> getReports () {
        List<WaterSourceReport> list = new ArrayList<WaterSourceReport>();
        this.reportsRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @RequestMapping(value="/api/sourcereports/create", method = RequestMethod.POST)
    WaterSourceReport createReport(@RequestBody WaterSourceReport input) {
        WaterSourceReport newReport = new WaterSourceReport(
                input.getTimestamp(),
                input.getReporterName(),
                input.getLongitude(),
                input.getLatitude(),
                input.getWaterCondition(),
                input.getWaterType());
        this.reportsRepository.save(newReport);
        return newReport;
    }

    @RequestMapping(value="/api/sourcereports/{reportId}", method = RequestMethod.PUT)
    WaterSourceReport createReport(@PathVariable Long reportId, @RequestBody WaterSourceReport input) {
        this.validateReportId(reportId);
        WaterSourceReport report = this.reportsRepository.findById(reportId).get();
        report.setLongitude(input.getLongitude());
        report.setLatitude(input.getLatitude());
        report.setWaterCondition(input.getWaterCondition());
        report.setWaterType(input.getWaterType());
        this.reportsRepository.save(report);
        return report;
    }

    private void validateReportId(Long userId) {
        this.reportsRepository
                .findById(userId)
                .orElseThrow(() -> new WaterQualityReportNotFoundException(userId));
    }
}
