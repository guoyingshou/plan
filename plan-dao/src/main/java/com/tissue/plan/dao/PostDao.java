package com.tissue.plan.dao;

import com.tissue.domain.plan.Post;
import java.util.List;

public interface PostDao {

    /**
     * Add a post.
     */
    Post create(Post post);

    /**
     * Get the specific post.
     */
    Post getPost(String id);

    //-- by topic
    List<Post> getPostsByTopicId(String topicId);

    List<Post> getPostsByTopicIdAndType(String topicId, String type);

    //-- by plan

    List<Post> getPostsByPlanId(String planId);

    //-- by user

    List<Post> getPostsByUserId(String userId);

}
