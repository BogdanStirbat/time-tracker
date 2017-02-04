package com.bstirbat.timetracker.dao;


import com.bstirbat.timetracker.model.Activity;

public interface ActivityDAO {

    Activity save(Activity activity);

    void remove(Long id);

    Activity find(String name);
}
