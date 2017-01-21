package com.bstirbat.timetracker.config;

import com.bstirbat.timetracker.service.ActivityDurationService;
import com.bstirbat.timetracker.service.impl.ActivityDurationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.bstirbat.timetracker")
public class AppConfig {

    @Bean("activityDurationService")
    public ActivityDurationService activityDurationService() {
        return new ActivityDurationServiceImpl();
    }
}
