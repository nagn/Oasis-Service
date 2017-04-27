package com.turboocelots.oasis.service.controllers;

import com.turboocelots.oasis.service.models.OasisUser;
import com.turboocelots.oasis.service.models.OasisUserRepository;
import com.turboocelots.oasis.service.models.SecurityLog;
import com.turboocelots.oasis.service.models.SecurityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mlin on 4/24/17.
 */
@RestController
public class SecurityLogController {
    private final SecurityLogRepository securityLogRepository;

    @Autowired
    SecurityLogController(SecurityLogRepository securityLogRepository) {
        this.securityLogRepository = securityLogRepository;
    }

    @RequestMapping(value= "/api/logs", method = RequestMethod.GET)
    Collection<SecurityLog> getLogs () {
        List<SecurityLog> list = new ArrayList<>();
        this.securityLogRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
