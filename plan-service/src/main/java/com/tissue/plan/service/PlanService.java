package com.tissue.plan.service;

import com.tissue.core.plan.Plan;
import com.tissue.core.plan.dao.PlanDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.joda.time.DateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class PlanService {

    @Autowired
    private PlanDao planDao;

    /**
    @Autowired
    private TopicDao topicDao;

    @Autowired
    private UserDao userDao;
    */

    /**
     * Save a plan.
     * The begins, ends, topicId, userId attributes must not be null.
     * @param plan
     * @return the newly add plan
     */
    public Plan addPlan(Plan plan) {
        return planDao.create(plan);
    }

    public Plan getPlan(String planId) {
        return planDao.getPlan(planId);
    }

    /**
    public List<Plan> getPlansByTopicId(String topicId) {
        return planDao.getPlansByTopicId(topicId);
    }
    */

    public void addMember(String planId, String userId) {
        planDao.addMember(planId, userId);
    }

}
