package com.tissue.plan.dao;

import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;
import java.util.List;

public interface TopicDao {

    Topic create(Topic topic);

    void update(Topic topic);

    Topic getTopic(String id);

    Topic getTopicByPlanId(String planId);

    List<Topic> getTrendingTopics();

    List<Topic> getFeaturedTopics();

    long getTopicsCount();

    List<Topic> getPagedTopics(int page, int size);

    List<Topic> getTopics();

    long getTopicsCountByTag(String tag);

    List<String> getTopicTags();

    List<Topic> getPagedTopicsByTag(String tag, int page, int size);

    List<Topic> getTopicsByTag(String tag);

    void addPlan(Plan plan);

}
