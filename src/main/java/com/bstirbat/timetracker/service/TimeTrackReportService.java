package com.bstirbat.timetracker.service;


import com.bstirbat.timetracker.model.TimeTrackReport;

import java.util.Date;
import java.util.List;

public interface TimeTrackReportService {

    TimeTrackReport save(TimeTrackReport timeTrackReport);

    void remove(Long id);

    /**
     * Retrieves all time track reports with the specified date.
     * @param date the date
     * @return list of reports
     */
    List<TimeTrackReport> retrieveAllWithDate(Date date);

    /**
     * Retrieves all time track reports within the time interval.
     * @param from start of interval (closed)
     * @param to end of interval (open)
     * @return
     */
    List<TimeTrackReport> retrieveAllWithinInterval(Date from, Date to);
}
