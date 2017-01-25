package com.bstirbat.timetracker.service;


import com.bstirbat.timetracker.dao.TimeTrackReportDAO;
import com.bstirbat.timetracker.model.TimeTrackReport;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeTrackReportServiceImpl implements TimeTrackReportService {

    private TimeTrackReportDAO timeTrackReportDAO;

    public TimeTrackReportServiceImpl(TimeTrackReportDAO timeTrackReportDAO) {
        this.timeTrackReportDAO = timeTrackReportDAO;
    }

    @Override
    public void save(TimeTrackReport timeTrackReport) {
        timeTrackReportDAO.save(timeTrackReport);
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
}
