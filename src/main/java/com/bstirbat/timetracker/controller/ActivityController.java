package com.bstirbat.timetracker.controller;


import com.bstirbat.timetracker.model.Activity;
import com.bstirbat.timetracker.response.api.OperationResponse;
import com.bstirbat.timetracker.service.ActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    @Qualifier("activityService")
    private ActivityService activityService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addActivity(@RequestBody Activity activity) {
        LOGGER.info("Received persist request for activity=<{}>", activity);

        try {
            Activity persistedActivity = activityService.save(activity);
            return objectMapper.writeValueAsString(new OperationResponse(true, persistedActivity.getId()));
        } catch (Exception e) {
            LOGGER.error("An error appeared saving activity=<{}>", activity, e);
            return "{\"success\": false}";
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeTimeTrackReport(@RequestParam("id") Long id) {
        LOGGER.info("Received remove request for activity with id=<{}>", id);

        try {
            activityService.remove(id);
            return objectMapper.writeValueAsString(new OperationResponse(true, id));
        } catch (Exception e) {
            LOGGER.error("An error appeared removing activity with id=<{}>", id, e);
            return "{\"success\": false}";
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public String retrieveAllActivities() {
        LOGGER.info("Received retrieveAllActivities activities");

        try {
            List<Activity> activities = activityService.retrieveAll();
            return objectMapper.writeValueAsString(activities);
        } catch (Exception e) {
            LOGGER.error("An error ocurred retrieving all activities", e);
            return "{\"success\": false}";
        }
    }
}
