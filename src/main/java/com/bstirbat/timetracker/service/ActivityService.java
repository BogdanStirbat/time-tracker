package com.bstirbat.timetracker.service;


import com.bstirbat.timetracker.model.Activity;

public interface ActivityService {

    Activity save(Activity activity);

    void remove(Long id);
}
