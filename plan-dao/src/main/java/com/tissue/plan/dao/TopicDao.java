package com.tissue.plan.dao;

import com.tissue.domain.plan.Topic;
import java.util.List;

public interface TopicDao {

    Topic create(Topic topic);

    Topic getTopic(String id);

    List<Topic> getTopics();

    List<String> getTopicTags();

    List<Topic> getTopicsByTag(String tag);




    /**
     * Get a topic with a subset of the fields.
     * @param topicId topic id
     * @return a topic with fields of id, title
     */
    //Topic getTopicMinium(String id);

    //long getTopicsCount();


    //List<Topic> getTopics(Integer currentPage, Integer pageSize);

    //Topic updateTopic(Topic topic);

    /**
    Integer getTopicsTotalCount();

    Integer getTopicsByUserCount(@Param("userId") Integer userId);

    List<Topic> getTopics(@Param("pageSize") Integer id, @Param("offset") Integer offset);

    List<Topic> getTopicsByUser(@Param("pageSize") Integer id, @Param("offset") Integer offset, @Param("userId") Integer userId);

    Topic getTopic(@Param("id") Integer id);

    Topic getTopicFull(@Param("id") Integer id);

    Integer update(Topic topic);

    Integer delete(@Param("id") Integer id);

    List<Topic> getUserTopics(@Param("userId") Integer userId);

    List<Plan> getPlans(@Param("id") Integer id);
    */

}
