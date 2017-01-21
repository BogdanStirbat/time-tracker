package com.bstirbat.timetracker.service.impl;


import com.bstirbat.timetracker.service.ActivityDurationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ActivityDurationServiceImpl implements ActivityDurationService {

    @PersistenceContext
    EntityManager em;


}
