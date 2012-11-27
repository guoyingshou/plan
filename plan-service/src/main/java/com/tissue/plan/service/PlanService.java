package com.tissue.plan.service;

import com.tissue.domain.plan.Plan;
import com.tissue.plan.dao.PlanDao;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;

@Component
public class PlanService {

    @Autowired
    private PlanDao planDao;

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

    public void addMember(String planId, String userId) {
        planDao.addMember(planId, userId);
    }

    /**
    public Plan getPlanMinium(long planId) {
        return planDao.getPlanMinium(planId);
    }
    */

    /**
     * Helper method to determine the active plan.
     * An active plan is one that has not expired.
    public Plan getActivePlan(String topicId) {
        List<Plan> plans = planDao.getPlans(topicId);
        for(Plan plan : plans) {
            if(plan.isActive())
                return plan;
        }
        return null;
    }

    public List<Plan> getDeadPlans(String topicId) {
        List<Plan> result = new ArrayList();

        List<Plan> plans = planDao.getPlans(topicId);
        for(Plan plan : plans) {
            if(!plan.isActive()) {
                result.add(plan);
            }
        }
        return result;
    }
     */
}
