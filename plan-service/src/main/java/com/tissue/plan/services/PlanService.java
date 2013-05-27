package com.tissue.plan.services;

import com.tissue.core.Account;
import com.tissue.plan.command.PlanCommand;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Post;
import com.tissue.plan.dao.TopicDao;
import com.tissue.plan.dao.PlanDao;
import com.tissue.plan.dao.PostDao;

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

    /**
    public Plan getPlan(String planId) {
        return planDao.getPlan(planId);
    }
    */

    public void addMember(String planId, String accountId) {
        planDao.addMember(planId, accountId);
    }

    /**
     * topic
    public Topic getTopic(String planId) {
        return topicDao.getTopicByPlan(planId);
    }
     */

    /**
     * post
     */
    public long getPostsCount(String planId) {
        return postDao.getPostsCountByPlan(planId);
    }

    public List<Post> getPagedPosts(String planId, int page, int size) {
        return postDao.getPagedPostsByPlan(planId, page, size);
    }

    public int getViewerActivePlansCount(String viewerAccountId) {
        if(viewerAccountId == null) {
            return 0;
        }

        int count = 0;
        List<Plan> plans = planDao.getPlansByAccount(viewerAccountId);
        for(Plan plan : plans) {
            if(plan.isActive()) {
                count++;
            }
        }
        return count;
    }

}
