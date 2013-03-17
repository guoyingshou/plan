package com.tissue.plan.services;

import com.tissue.core.command.PlanCommand;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.dao.TopicDao;
import com.tissue.core.plan.dao.PlanDao;
import com.tissue.core.plan.dao.PostDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.util.List;

@Component
public class PlanService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PlanDao planDao;

    @Resource(name="postDaoImpl")
    private PostDao postDao;

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

    public void addMember(String planId, String accountId) {
        planDao.addMember(planId, accountId);
    }

    /**
    public Boolean isMember(String planId, String accountId) {
        return planDao.isMember(planId, accountId);
    }
    */

    /**
     * topic
     */
    public Topic getTopic(String planId) {
        return topicDao.getTopicByPlan(planId);
    }

    /**
     * post
     */
    public long getPostsCount(String planId) {
        return postDao.getPostsCountByPlan(planId);
    }

    public List<Post> getPagedPosts(String planId, int page, int size) {
        return postDao.getPagedPostsByPlan(planId, page, size);
    }

}
