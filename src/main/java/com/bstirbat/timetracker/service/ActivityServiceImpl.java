package com.bstirbat.timetracker.service;


import com.bstirbat.timetracker.dao.ActivityDAO;
import com.bstirbat.timetracker.model.Activity;

import java.util.List;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDAO activityDAO;

    public ActivityServiceImpl(ActivityDAO activityDAO) {
        this.activityDAO = activityDAO;
    }

    @Override
    public Activity save(Activity activity) {
        return activityDAO.save(activity);
    }

    @Override
    public void remove(Long id) {
        activityDAO.remove(id);
    }

    @Override
    public List<Activity> retrieveAll() {
        return activityDAO.retrieveAll();
    }
}
