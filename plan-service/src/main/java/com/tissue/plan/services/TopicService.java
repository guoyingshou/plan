package com.tissue.plan.services;

//import com.tissue.core.command.ItemCommand;
import com.tissue.core.command.TopicCommand;
import com.tissue.core.social.User;
import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.dao.TopicDao;
import com.tissue.core.plan.dao.PlanDao;
import com.tissue.core.orient.dao.CommonDao;
import com.tissue.commons.exceptions.IllegalAccessException;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Component
public class TopicService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PlanDao planDao;

    /**
     * Save a topic.
     * The title, content, userId attributes must be not null.
     * @param topic
     * @return id of the topic created.
     */
    public String addTopic(TopicCommand command) {
        return topicDao.create(command);
    }

    public void updateTopic(TopicCommand command) {
        topicDao.update(command);
    }

    public void deleteTopic(String topicId) {
        commonDao.delete(topicId);
    }

    public Topic getTopic(String topicId) {
        return topicDao.getTopic(topicId);
    }

    public List<Topic> getTrendingTopics(int num) {
        return topicDao.getTrendingTopics(num);
    }

    public List<Topic> getFeaturedTopics(int num) {
        return topicDao.getFeaturedTopics(num);
    }

    public long getTopicsCount() {
        return topicDao.getTopicsCount();
    }

    public List<Topic> getPagedTopics(int page, int size) {
        return topicDao.getPagedTopics(page, size);
    }

    public List<String> getTopicTags() {
        return topicDao.getTopicTags();
    }

    public long getTopicsCountByTag(String tag) {
        return topicDao.getTopicsCountByTag(tag);
    }

    public List<Topic> getPagedTopicsByTag(String tag, int page, int size) {
        return topicDao.getPagedTopicsByTag(tag, page, size);
    }

    /**
    public List<Topic> getNewTopics(String excludingUserId, int limit) {
        return topicDao.getNewTopics(excludingUserId, limit);
    }
    */

    /**
     * Post
     */
    public long getPostsCount(String topicId) {
        return topicDao.getPostsCount(topicId);
    }

    public List<Post> getPagedPosts(String topicId, int page, int size) {
        return topicDao.getPagedPosts(topicId, page, size);
    }

    public long getPostsCountByType(String topicId, String type) {
        return topicDao.getPostsCountByType(topicId, type);
    }

    public List<Post> getPagedPostsByType(String topicId, String type, int page, int size) {
        return topicDao.getPagedPostsByType(topicId, type, page, size);
    }

    public void checkOwner(Topic topic, Account viewerAccount) {
        Account ownerAccount = topic.getAccount();
        if(!viewerAccount.equals(ownerAccount)) {
            throw new IllegalAccessException("illegal access: " + topic.getId() + ", viewer: " + viewerAccount.getUsername());
        }
    }

    public void checkActivePlan(Topic topic, Account viewerAccount) {
        Plan activePlan = topic.getActivePlan();
        if(activePlan == null) {
            throw new IllegalArgumentException("no active plan for topic: " + topic.getId());
        }
        if(!activePlan.isOwner(viewerAccount.getId()) && !activePlan.isMember(viewerAccount.getId())) {
            throw new IllegalArgumentException(viewerAccount.getUsername() + " is not owner or member for active plan in topic: " + topic.getId());
        }
    }

}
