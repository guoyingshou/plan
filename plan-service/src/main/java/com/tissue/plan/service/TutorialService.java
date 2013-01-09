package com.tissue.plan.service;

import com.tissue.core.profile.User;
import com.tissue.core.plan.Tutorial;
import com.tissue.core.plan.dao.TutorialDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class TutorialService {

    @Autowired
    private TutorialDao tutorialDao;

    /**
    @Autowired
    private PostMessageDao postMessageDao;
    */

    /**
     * Save a tutorial.
     * 
     * @param tutorial
     * @return the newly added tutorial
     */
    public Tutorial addTutorial(Tutorial tutorial) {
        tutorial = tutorialDao.create(tutorial);
        return tutorial;
    }
}
