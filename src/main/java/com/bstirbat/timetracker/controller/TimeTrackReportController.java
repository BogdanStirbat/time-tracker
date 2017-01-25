package com.bstirbat.timetracker.controller;


import com.bstirbat.timetracker.model.TimeTrackReport;
import com.bstirbat.timetracker.response.api.OperationResponse;
import com.bstirbat.timetracker.service.TimeTrackReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/report")
public class TimeTrackReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTrackReportController.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    @Qualifier("timeTrackReportService")
    private TimeTrackReportService timeTrackReportService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addTimeTrackReport(@RequestBody TimeTrackReport timeTrackReport) {
        LOGGER.info("Received persist request for timeTrackReport=<{}>", timeTrackReport);

        try {
            TimeTrackReport persistedTimeTrackReport = timeTrackReportService.save(timeTrackReport);
            return objectMapper.writeValueAsString(new OperationResponse(true, persistedTimeTrackReport.getId()));
        } catch (Exception e) {
            LOGGER.error("An error appeared saving timeTrackReport=<{}>", timeTrackReport, e);
            return "{\"success\": false}";
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeTimeTrackReport(@RequestParam("id") Long id) {
        LOGGER.info("Received remove request for timeTrackReport with id=<{}>", id);

        try {
            timeTrackReportService.remove(id);
            return objectMapper.writeValueAsString(new OperationResponse(true, id));
        } catch (Exception e) {
            LOGGER.error("An error appeared removing timeTrackReport with id=<{}>", id, e);
            return "{\"success\": false}";
        }
    }

    @RequestMapping(value = "/all/byDay", method = RequestMethod.GET)
    @ResponseBody
    public String retrieveTimeTrackReportsByDay(@RequestParam("day") @DateTimeFormat(pattern="yyyy-MM-dd") Date day) {
        LOGGER.info("Received retrieve all request, day=<{}>", day);

        try {
            List<TimeTrackReport> timeTrackReports = timeTrackReportService.retrieveAllWithDate(day);
            return objectMapper.writeValueAsString(timeTrackReports);
        } catch (Exception e) {
            LOGGER.error("An error ocurred receiving all time track reports, day=<{}>", day, e);
            return "{\"success\": false}";
        }
    }
}
