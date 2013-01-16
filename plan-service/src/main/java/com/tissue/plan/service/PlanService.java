package com.tissue.plan.service;

import com.tissue.core.social.User;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.dao.PlanDao;
import com.tissue.core.plan.dao.TopicDao;
import com.tissue.core.social.dao.UserDao;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class PlanService {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private TopicDao topicDao;

    /**
    @Autowired
    private EventDao eventDao;
    */

    @Autowired
    private UserDao userDao;

    /**
     * Save a plan.
     * The begins, ends, topicId, userId attributes must not be null.
     * @param plan
     * @return the newly add plan
     */
    public Plan addPlan(Plan plan) {
        plan = planDao.create(plan);
        //topicDao.addPlan(plan);

        return plan;
    }

    public Plan getPlan(String planId) {
        return planDao.getPlan(planId);
    }

    public void addMember(String planId, String userId) {
        planDao.addMember(planId, userId);

        /**
        Plan plan = planDao.getPlan(planId);
        User user = userDao.getUserById(userId);
        Event event = EventFactory.createEvent(plan, user);
        eventDao.addEvent(event);
        */
    }

}
