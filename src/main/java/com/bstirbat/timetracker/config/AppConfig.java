package com.bstirbat.timetracker.config;

import com.bstirbat.timetracker.dao.TimeTrackReportDAO;
import com.bstirbat.timetracker.dao.TimeTrackReportDAOImpl;
import com.bstirbat.timetracker.service.TimeTrackReportService;
import com.bstirbat.timetracker.service.TimeTrackReportServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.bstirbat.timetracker")
public class AppConfig {

    @Bean("timeTrackReportDAO")
    public TimeTrackReportDAO timeTrackReportDAO() {
        return new TimeTrackReportDAOImpl();
    }

    @Bean("timeTrackReportService")
    public TimeTrackReportService timeTrackReportService() {
        return new TimeTrackReportServiceImpl(timeTrackReportDAO());
    }
}
