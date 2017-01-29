package com.bstirbat.timetracker.dao;


import com.bstirbat.timetracker.model.Activity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ActivityDAOImpl implements ActivityDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Activity save(Activity activity) {
        return em.merge(activity);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Activity activity = em.find(Activity.class, id);
        em.remove(activity);
    }
}
