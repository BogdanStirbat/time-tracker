package com.bstirbat.timetracker.service;


import com.bstirbat.timetracker.model.TimeTrackReport;

import java.util.Date;
import java.util.List;

public interface TimeTrackReportService {

    void save(TimeTrackReport timeTrackReport);

    /**
     * Retrieves all time track reports with the specified date.
     * @param date the date
     * @return list of reports
     */
    List<TimeTrackReport> retrieveAllWithDate(Date date);
}
