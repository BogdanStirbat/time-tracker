package com.bstirbat.timetracker.config;

import com.bstirbat.timetracker.dao.ActivityDAO;
import com.bstirbat.timetracker.dao.ActivityDAOImpl;
import com.bstirbat.timetracker.dao.TimeTrackReportDAO;
import com.bstirbat.timetracker.dao.TimeTrackReportDAOImpl;
import com.bstirbat.timetracker.service.ActivityService;
import com.bstirbat.timetracker.service.ActivityServiceImpl;
import com.bstirbat.timetracker.service.TimeTrackReportService;
import com.bstirbat.timetracker.service.TimeTrackReportServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.bstirbat.timetracker")
public class AppConfig {

    @Bean("activityDAO")
    public ActivityDAO activityDAO() {
        return new ActivityDAOImpl();
    }

    @Bean("activityService")
    public ActivityService activityService() {
        return new ActivityServiceImpl(activityDAO());
    }

    @Bean("timeTrackReportDAO")
    public TimeTrackReportDAO timeTrackReportDAO() {
        return new TimeTrackReportDAOImpl();
    }

    @Bean("timeTrackReportService")
    public TimeTrackReportService timeTrackReportService() {
        return new TimeTrackReportServiceImpl(activityDAO(), timeTrackReportDAO());
    }
}
