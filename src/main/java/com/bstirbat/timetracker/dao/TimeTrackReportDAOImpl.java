package com.bstirbat.timetracker.dao;


import com.bstirbat.timetracker.model.TimeTrackReport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TimeTrackReportDAOImpl implements TimeTrackReportDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void save(TimeTrackReport timeTrackReport) {
        em.merge(timeTrackReport);
    }
}
