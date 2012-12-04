package com.tissue.plan.service;

import com.tissue.domain.social.Event;
import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;
import com.tissue.plan.dao.TopicDao;
import com.tissue.plan.dao.PlanDao;
import com.tissue.commons.util.EventFactory;
import com.tissue.commons.dao.social.EventDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Component
public class TopicService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PlanDao planDao;

    @Autowired
    private EventDao eventDao;

    /**
     * Save a topic.
     * The title, content, userId attributes must be not null.
     * @param topic
     * @return the returned topic has id setup
     */
    public Topic addTopic(Topic topic) {
        topic = topicDao.create(topic);

        //generate event
        Event event = EventFactory.createEvent(topic);
        eventDao.addEvent(event);

        return topic;
    }

    public Topic getTopic(String topicId) {
        return topicDao.getTopic(topicId);
    }

    public List<Topic> getTrendingTopics() {
        return topicDao.getTrendingTopics();
    }

    public List<Topic> getFeaturedTopics() {
        return topicDao.getFeaturedTopics();
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

}
