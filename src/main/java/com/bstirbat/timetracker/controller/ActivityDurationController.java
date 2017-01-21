package com.bstirbat.timetracker.controller;


import com.bstirbat.timetracker.service.ActivityDurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActivityDurationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityDurationController.class);

    @Autowired
    @Qualifier("activityDurationService")
    private ActivityDurationService activityDurationService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String testConfiguration() {
        LOGGER.info("Test called.");
        return "{\"success\": 1}";
    }
}
