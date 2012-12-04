package com.tissue.plan.dao;

import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;
import java.util.List;

public interface TopicDao {

    Topic create(Topic topic);

    Topic getTopic(String id);

    List<Topic> getTrendingTopics();

    List<Topic> getFeaturedTopics();

    List<Topic> getTopics();

    List<String> getTopicTags();

    List<Topic> getTopicsByTag(String tag);

    void addPlan(Plan plan);

}
