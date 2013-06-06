package com.tissue.plan.services;

import com.tissue.core.User;
import com.tissue.core.Account;
import com.tissue.plan.command.TopicCommand;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Post;
import com.tissue.plan.Question;
import com.tissue.plan.dao.PostDao;
import com.tissue.plan.dao.TopicDao;
import com.tissue.plan.dao.PlanDao;
import com.tissue.plan.dao.PostDao;
import com.tissue.plan.dao.QuestionDao;
import com.tissue.commons.services.ContentService;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.security.AccessControlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TopicService extends ContentService {

    private static Logger logger = LoggerFactory.getLogger(TopicService.class);

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PlanDao planDao;

    @Resource(name="postDaoImpl")
    private PostDao postDao;

    @Autowired
    private QuestionDao questionDao;

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

    public Set<String> getTopicTags() {
        return topicDao.getTopicTags();
    }

    public long getTopicsCountByTag(String tag) {
        return topicDao.getTopicsCountByTag(tag);
    }

    public List<Topic> getPagedTopicsByTag(String tag, int page, int size) {
        return topicDao.getPagedTopicsByTag(tag, page, size);
    }

    /**
     * Post
     */
    public long getPostsCount(String topicId) {
        return postDao.getPostsCountByTopic(topicId);
    }

    public List<Post> getPagedPosts(String topicId, int page, int size) {
        return postDao.getPagedPostsByTopic(topicId, page, size);
    }

    public long getPostsCountByType(String topicId, String type) {
        return postDao.getPostsCountByType(topicId, type);
    }

    public List<Post> getPagedPostsByType(String topicId, String type, int page, int size) {
        return postDao.getPagedPostsByType(topicId, type, page, size);
    }

    /**
     * membership
     */
    public boolean isMember(Topic topic, Account account) {

        if(account == null || topic == null) {
            logger.debug("Null account");
            return false;
        }

        if(!account.hasRole("ROLE_USER") || account.hasRole("ROLE_EVIL")) {
            logger.debug("ROLE_EVIL or No role of name: 'ROLE_USER'");
            return false;
        }

        Plan plan = topic.getActivePlan();
        if(plan == null) {
            logger.debug("Null plan");
            return false;
        }

        return planDao.isMember(plan.getId(), account.getId());
    }

    public void checkMembership(Topic topic, Account account) {
        if(!isMember(topic, account)) {
            throw new AccessControlException(account + " is not memeber of the active plan in " + topic);
        }
    }
 
}
