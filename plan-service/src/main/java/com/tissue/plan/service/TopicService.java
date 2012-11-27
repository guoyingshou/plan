package com.tissue.plan.service;

import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;
import com.tissue.plan.dao.TopicDao;
import com.tissue.plan.dao.PlanDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class TopicService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PlanDao planDao;

    /**
     * Save a topic.
     * The title, content, userId attributes must be not null.
     * @param topic
     * @return the returned topic has id setup
     */
    public Topic addTopic(Topic topic) {
        return topicDao.create(topic);
    }

    public Topic getTopic(String topicId) {
        return topicDao.getTopic(topicId);
    }

    public List<Topic> getTopics() {
        return topicDao.getTopics();
    }

    public List<String> getTopicTags() {
        return topicDao.getTopicTags();
    }

    public List<Topic> getTopicsByTag(String tag) {
        return topicDao.getTopicsByTag(tag);
    }




    /**
    public long getTopicsCount() {
        return topicDao.getTopicsCount();
    }

    public List<Topic> getTopics(Integer currentPage, Integer pageSize) {
        return topicDao.getTopics(currentPage, pageSize);
    }

    public Topic updateTopic(Topic topic) {
        return topicDao.updateTopic(topic);
    }
    */
}
