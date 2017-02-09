package com.bstirbat.timetracker;

import com.bstirbat.timetracker.config.AppConfig;
import com.bstirbat.timetracker.model.Activity;
import com.bstirbat.timetracker.model.TimeTrackReport;
import com.bstirbat.timetracker.response.api.OperationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class AppTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Transactional
    public void whenNoDataInserted_NoDataReturned() throws Exception {
        String date = "2017-01-16";

        this.mockMvc.perform(get("/activity/all")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        this.mockMvc.perform(get("/report/all/byDay?day=" + date)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @Transactional
    public void testWhenActivitiesAreAdded_AndRemoved_FullListIsCorrect() throws Exception{
        String programmingActivityName = "Programming";
        String learningActivityName = "Learning";
        String laundryActivityName = "Laundry";

        Activity programming =  new Activity();
        programming.setName(programmingActivityName);

        Activity learning = new Activity();
        learning.setName(learningActivityName);

        Activity laundry = new Activity();
        laundry.setName(laundryActivityName);

        this.mockMvc.perform(post("/activity/add")
                .content(OBJECT_MAPPER.writeValueAsString(programming))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new ActivityMatcher(programming)));

        this.mockMvc.perform(post("/activity/add")
                .content(OBJECT_MAPPER.writeValueAsString(learning))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new ActivityMatcher(learning)));

        this.mockMvc.perform(post("/activity/add")
                .content(OBJECT_MAPPER.writeValueAsString(laundry))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new ActivityMatcher(laundry)));

        this.mockMvc.perform(get("/activity/all")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(Arrays.asList(programming, learning, laundry)), false));

        this.mockMvc.perform(delete("/activity/remove?id=" + laundry.getId())
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(new OperationResponse(true, laundry.getId())), false));

        this.mockMvc.perform(get("/activity/all")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(Arrays.asList(programming, learning)), false));
    }

    @Test
    @Transactional
    public void testActivities_AndReposts_AreCorrelatedCorrectly() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String firstDate = "2017-01-16";
        String secondDate = "2017-01-17";
        String thirdDate = "2017-01-18";
        String programmingActivityName = "Programming";
        String learningActivityName = "Learning";
        String laundryActivityName = "Laundry";

        Activity programming =  new Activity();
        programming.setName(programmingActivityName);

        Activity learning = new Activity();
        learning.setName(learningActivityName);

        Activity laundry = new Activity();
        laundry.setName(laundryActivityName);

        TimeTrackReport firstReport = new TimeTrackReport();
        firstReport.setActivityName(programmingActivityName);
        firstReport.setDate(dateFormat.parse(firstDate));
        firstReport.setHours(2);
        firstReport.setMinutes(30);

        TimeTrackReport secondReport = new TimeTrackReport();
        secondReport.setActivityName(learningActivityName);
        secondReport.setDate(dateFormat.parse(firstDate));
        secondReport.setHours(2);
        secondReport.setMinutes(30);

        TimeTrackReport thirdReport = new TimeTrackReport();
        thirdReport.setActivityName(laundryActivityName);
        thirdReport.setDate(dateFormat.parse(secondDate));
        thirdReport.setHours(1);

        this.mockMvc.perform(post("/report/add")
                .content(OBJECT_MAPPER.writeValueAsString(firstReport))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"success\": false}"));

        this.mockMvc.perform(post("/activity/add")
                .content(OBJECT_MAPPER.writeValueAsString(programming))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new ActivityMatcher(programming)));

        this.mockMvc.perform(post("/activity/add")
                .content(OBJECT_MAPPER.writeValueAsString(learning))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new ActivityMatcher(learning)));

        this.mockMvc.perform(post("/activity/add")
                .content(OBJECT_MAPPER.writeValueAsString(laundry))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new ActivityMatcher(laundry)));

        this.mockMvc.perform(post("/report/add")
                .content(OBJECT_MAPPER.writeValueAsString(firstReport))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new TimeTrackReportMatcher(firstReport)));

        this.mockMvc.perform(post("/report/add")
                .content(OBJECT_MAPPER.writeValueAsString(secondReport))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new TimeTrackReportMatcher(secondReport)));

        this.mockMvc.perform(post("/report/add")
                .content(OBJECT_MAPPER.writeValueAsString(thirdReport))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new TimeTrackReportMatcher(thirdReport)));

        this.mockMvc.perform(get("/report/all/byDay?day=" + firstDate)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(Arrays.asList(firstReport, secondReport)), false));

        this.mockMvc.perform(get("/report/all/byDay?day=" + secondDate)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(Arrays.asList(thirdReport)), false));

        this.mockMvc.perform(get("/report/all/byInterval?from=" + firstDate + "&to=" + secondDate)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(Arrays.asList(firstReport, secondReport)), false));

        this.mockMvc.perform(get("/report/all/byInterval?from=" + firstDate + "&to=" + thirdDate)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(Arrays.asList(firstReport, secondReport, thirdReport)), false));

        this.mockMvc.perform(delete("/report/remove?id=" + firstReport.getId())
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(new OperationResponse(true, firstReport.getId())), false));

        this.mockMvc.perform(get("/report/all/byDay?day=" + firstDate)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(Arrays.asList(secondReport)), false));

        this.mockMvc.perform(get("/report/all/byInterval?from=" + firstDate + "&to=" + thirdDate)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(Arrays.asList(secondReport, thirdReport)), false));
    }

    static class ActivityMatcher extends BaseMatcher<String> {

        private Activity activity;

        public ActivityMatcher(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean matches(Object item) {
            if (!(item instanceof String)) {
                return false;
            }

            String receivedJson = (String) item;

            OperationResponse response = null;
            try {
                response = OBJECT_MAPPER.readValue(receivedJson, OperationResponse.class);
            } catch (Exception e) {
                return false;
            }

            activity.setId(response.getId());

            return response.isSuccess() && response.getId() > 0;
        }

        @Override
        public void describeTo(Description description) {

        }
    }

    static class TimeTrackReportMatcher extends BaseMatcher<String> {

        private TimeTrackReport timeTrackReport;

        public TimeTrackReportMatcher(TimeTrackReport timeTrackReport) {
            this.timeTrackReport = timeTrackReport;
        }

        @Override
        public boolean matches(Object item) {
            if (!(item instanceof String)) {
                return false;
            }

            String receivedJson = (String) item;

            OperationResponse response = null;
            try {
                response = OBJECT_MAPPER.readValue(receivedJson, OperationResponse.class);
            } catch (Exception e) {
                return false;
            }

            timeTrackReport.setId(response.getId());
            return response.isSuccess() && response.getId() > 0;
        }

        @Override
        public void describeTo(Description description) {

        }
    }
}
