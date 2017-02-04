package com.bstirbat.timetracker.dao;


import com.bstirbat.timetracker.model.Activity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ActivityDAOImpl implements ActivityDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Activity save(Activity activity) {
        if (activity == null || StringUtils.isEmpty(activity.getName())) {
            throw new IllegalArgumentException("Given activity is invalid!");
        }

        Activity existingActivity = find(activity.getName());
        if (existingActivity != null) {
            throw new IllegalArgumentException("An activity with the given name was already added!");
        }

        return em.merge(activity);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Activity activity = em.find(Activity.class, id);
        em.remove(activity);
    }

    @Override
    public Activity find(String name) {
        List<Activity> activities = em.createQuery("select a from Activity a where a.name=:name", Activity.class)
                .setParameter("name", name)
                .getResultList();

        if (activities == null || activities.isEmpty()) {
            return null;
        }

        return activities.get(0);
    }
}
