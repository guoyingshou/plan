package com.tissue.plan.services;

import com.tissue.core.command.PlanCommand;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.dao.PlanDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public String addPlan(PlanCommand plan) {
        return planDao.create(plan);
    }

    public Plan getPlan(String planId) {
        return planDao.getPlan(planId);
    }

    public void addMember(String planId, String userId) {
        planDao.addMember(planId, userId);
    }

    /**
     * topic
     */
    public Topic getTopic(String planId) {
        return planDao.getTopic(planId);
    }

    /**
     * post
     */
    public long getPostsCount(String planId) {
        return planDao.getPostsCount(planId);
    }

    public List<Post> getPagedPosts(String planId, int page, int size) {
        return planDao.getPagedPosts(planId, page, size);
    }



}
