package com.bstirbat.timetracker.service;


import com.bstirbat.timetracker.dao.TimeTrackReportDAO;
import com.bstirbat.timetracker.model.TimeTrackReport;

public class TimeTrackReportServiceImpl implements TimeTrackReportService {

    private TimeTrackReportDAO timeTrackReportDAO;

    public TimeTrackReportServiceImpl(TimeTrackReportDAO timeTrackReportDAO) {
        this.timeTrackReportDAO = timeTrackReportDAO;
    }

    @Override
    public void save(TimeTrackReport timeTrackReport) {
        timeTrackReportDAO.save(timeTrackReport);
    }
}
