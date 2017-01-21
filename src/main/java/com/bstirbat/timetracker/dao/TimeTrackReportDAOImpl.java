package com.bstirbat.timetracker.dao;


import com.bstirbat.timetracker.model.TimeTrackReport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

public class TimeTrackReportDAOImpl implements TimeTrackReportDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void save(TimeTrackReport timeTrackReport) {
        em.merge(timeTrackReport);
    }

    @Override
    @Transactional
    public List<TimeTrackReport> retrieveAllBetween(Date fistDate, Date secondDate) {
        return em.createQuery("SELECT t FROM TimeTrackReport t WHERE t.date >= :fistDate AND t.date < :secondDate", TimeTrackReport.class)
                .setParameter("fistDate", fistDate)
                .setParameter("secondDate", secondDate)
                .getResultList();
    }
}
