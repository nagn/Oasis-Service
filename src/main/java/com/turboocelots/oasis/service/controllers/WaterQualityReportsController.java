package com.turboocelots.oasis.service.controllers;

import com.turboocelots.oasis.service.exceptions.UserNotFoundException;
import com.turboocelots.oasis.service.exceptions.WaterQualityReportNotFoundException;
import com.turboocelots.oasis.service.models.OasisUserRepository;
import com.turboocelots.oasis.service.models.WaterQualityReport;
import com.turboocelots.oasis.service.models.WaterQualityReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mlin on 4/20/17.
 */
@RestController
public class WaterQualityReportsController {
    private final WaterQualityReportsRepository reportsRepository;
    private final OasisUserRepository userRepository;


    @Autowired
    WaterQualityReportsController(WaterQualityReportsRepository reportsRepository,
                                  OasisUserRepository userRepository) {
        this.reportsRepository = reportsRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value="/api/qualityreports", method = RequestMethod.GET)
    Collection<WaterQualityReport> getReports () {
        List<WaterQualityReport> list = new ArrayList<WaterQualityReport>();
        this.reportsRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @RequestMapping(value="/api/qualityreports/create", method = RequestMethod.POST)
    WaterQualityReport createReport(@RequestBody WaterQualityReport input) {
        WaterQualityReport newReport = new WaterQualityReport(
                input.getTimestamp(),
                input.getReporterName(),
                input.getLongitude(),
                input.getLatitude(),
                input.getOverallCondition(),
                input.getVirusPPM(),
                input.getContaminantsPPM());
        this.reportsRepository.save(newReport);
        return newReport;
    }

    @RequestMapping(value="/api/qualityreports/{reportID}", method = RequestMethod.PUT)
    WaterQualityReport updateReport(@PathVariable Long reportID, @RequestBody WaterQualityReport input) {
        this.validateReportID(reportID);
        WaterQualityReport report = this.reportsRepository.findById(reportID).get();
        report.setLongitude(input.getLongitude());
        report.setLatitude(input.getLatitude());
        report.setOverallCondition(input.getOverallCondition());
        report.setVirusPPM(input.getVirusPPM());
        report.setContaminantsPPM(input.getContaminantsPPM());
        this.reportsRepository.save(report);
        return report;
    }

    private void validateUserID(Long userId) {
        this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
    private void validateReportID(Long userId) {
        this.reportsRepository
                .findById(userId)
                .orElseThrow(() -> new WaterQualityReportNotFoundException(userId));
    }

}
