package com.bstirbat.timetracker.service;


import com.bstirbat.timetracker.model.Activity;

import java.util.List;

public interface ActivityService {

    Activity save(Activity activity);

    void remove(Long id);

    List<Activity> retrieveAll();
}
