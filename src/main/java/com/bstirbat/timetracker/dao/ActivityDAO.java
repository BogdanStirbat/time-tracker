package com.bstirbat.timetracker.dao;


import com.bstirbat.timetracker.model.Activity;

import java.util.List;

public interface ActivityDAO {

    Activity save(Activity activity);

    void remove(Long id);

    Activity find(String name);

    List<Activity> retrieveAll();
}
