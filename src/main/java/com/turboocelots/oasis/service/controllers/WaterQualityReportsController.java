package com.turboocelots.oasis.service.controllers;

import com.turboocelots.oasis.service.models.OasisUser;
import com.turboocelots.oasis.service.models.OasisUserRepository;
import com.turboocelots.oasis.service.models.WaterQualityReport;
import com.turboocelots.oasis.service.models.WaterQualityReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mlin on 4/20/17.
 */
@RestController
public class WaterQualityReportsController {
    private final WaterQualityReportsRepository reportsRepository;

    @Autowired
    WaterQualityReportsController(WaterQualityReportsRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    @RequestMapping(value="/api/qualityreports", method = RequestMethod.GET)
    Collection<WaterQualityReport> getReports () {
        List<WaterQualityReport> list = new ArrayList<WaterQualityReport>();
        this.reportsRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

}
