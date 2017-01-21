package com.bstirbat.timetracker.dao;


import com.bstirbat.timetracker.model.TimeTrackReport;

import java.util.Date;
import java.util.List;

public interface TimeTrackReportDAO {

    void save(TimeTrackReport timeTrackReport);

    /**
     * Retrieves all time tracking reports, between the 2 dates.
     * @param fistDate all reports having a date greater or equal to this
     * @param secondDate  all reports having a date less than this
     * @return list of reports; first date is closed interval, second is open
     */
    List<TimeTrackReport> retrieveAllBetween(Date fistDate, Date secondDate);

}
