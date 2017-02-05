package com.bstirbat.timetracker.service;


import com.bstirbat.timetracker.dao.ActivityDAO;
import com.bstirbat.timetracker.dao.TimeTrackReportDAO;
import com.bstirbat.timetracker.model.Activity;
import com.bstirbat.timetracker.model.TimeTrackReport;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeTrackReportServiceImpl implements TimeTrackReportService {

    private ActivityDAO activityDAO;

    private TimeTrackReportDAO timeTrackReportDAO;

    public TimeTrackReportServiceImpl(ActivityDAO activityDAO, TimeTrackReportDAO timeTrackReportDAO) {
        this.activityDAO = activityDAO;
        this.timeTrackReportDAO = timeTrackReportDAO;
    }

    @Override
    public TimeTrackReport save(TimeTrackReport timeTrackReport) {
        if (timeTrackReport == null || StringUtils.isEmpty(timeTrackReport.getActivityName())) {
            throw new IllegalArgumentException("Given time track report is invalid");
        }

        Activity givenActivity = activityDAO.find(timeTrackReport.getActivityName());
        if (givenActivity == null) {
            throw new IllegalArgumentException("No activity with name: " + timeTrackReport.getActivityName() + " was found!");
        }

        return timeTrackReportDAO.save(timeTrackReport);
    }

    @Override
    public void remove(Long id) {
        timeTrackReportDAO.remove(id);
    }

    @Override
    public List<TimeTrackReport> retrieveAllWithDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date firstDate = new Date(calendar.getTimeInMillis());
        Date secondDate = new Date(calendar.getTimeInMillis() + 24*60*60*1000 - 1);

        return timeTrackReportDAO.retrieveAllBetween(firstDate, secondDate);
    }

    @Override
    public List<TimeTrackReport> retrieveAllWithinInterval(Date from, Date to) {
        return timeTrackReportDAO.retrieveAllBetween(from, to);
    }
}
