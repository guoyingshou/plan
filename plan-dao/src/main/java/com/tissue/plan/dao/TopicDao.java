package com.tissue.plan.dao;

import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;
import java.util.List;

public interface TopicDao {

    Topic create(Topic topic);

    Topic getTopic(String id);

    Topic getTopicByPlanId(String planId);

    List<Topic> getTrendingTopics();

    List<Topic> getFeaturedTopics();

    List<Topic> getTopics();

    List<Topic> getPagedTopics(int page, int size);

    long getTopicsCount();

    List<String> getTopicTags();

    List<Topic> getTopicsByTag(String tag);

    void addPlan(Plan plan);

}
