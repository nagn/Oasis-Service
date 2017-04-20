package com.turboocelots.oasis.service.controllers;

import com.turboocelots.oasis.service.exceptions.UserNotFoundException;
import com.turboocelots.oasis.service.models.OasisUser;
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
        this.validateUserID(input.getUserID());
        Long userID = input.getUserID();
        OasisUser user = this.userRepository.findById(userID).get();
        WaterQualityReport newReport = new WaterQualityReport(input.getTimestamp(), user);
        this.reportsRepository.save(newReport);
        return newReport;
    }

    private void validateUserID(Long userId) {
        this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

}
