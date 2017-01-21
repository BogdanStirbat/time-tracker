package com.bstirbat.timetracker.controller;


import com.bstirbat.timetracker.model.TimeTrackReport;
import com.bstirbat.timetracker.service.TimeTrackReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/report")
public class TimeTrackReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTrackReportController.class);

    @Autowired
    @Qualifier("timeTrackReportService")
    private TimeTrackReportService timeTrackReportService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addTimeTrackReport(@RequestBody TimeTrackReport timeTrackReport) {
        LOGGER.info("Received persist request for timeTrackReport=<{}>", timeTrackReport);

        try {
            timeTrackReportService.save(timeTrackReport);
            return "{\"success\": 1}";
        } catch (Exception e) {
            LOGGER.error("An error appeared saving timeTrackReport=<{}>", timeTrackReport, e);
            return "{\"success\": 0}";
        }

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String testConfiguration() {
        LOGGER.info("Test called.");
        return "{\"success\": 1}";
    }
}
