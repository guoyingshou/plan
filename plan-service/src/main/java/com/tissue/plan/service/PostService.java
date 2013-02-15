package com.tissue.plan.service;

import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.plan.command.PostCommand;
import com.tissue.core.plan.dao.PostDao;
import com.tissue.core.plan.dao.PostMessageDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private PostMessageDao postMessageDao;

    /**
     * @param postCommand 
     * @return id of the newly created post
     */
    public String createPost(PostCommand postCommand) {
        return postDao.create(postCommand);
    }

    public Post updatePost(Post post) {
        return postDao.update(post);
    }

    /**
     * Get a specific post.
     */
    public Post getPost(String postId) {
        return postDao.getPost(postId);
    }

    public long getPostsCountByTopicId(String topicId) {
        return postDao.getPostsCountByTopicId(topicId);
    }

    public List<Post> getPagedPostsByTopicId(String topicId, int page, int size) {
        return postDao.getPagedPostsByTopicId(topicId, page, size);
    }

    public long getPostsCountByTopicIdAndType(String topicId, String type) {
        return postDao.getPostsCountByTopicIdAndType(topicId, type);
    }

    public List<Post> getPagedPostsByTopicIdAndType(String topicId, String type, int page, int size) {
        return postDao.getPagedPostsByTopicIdAndType(topicId, type, page, size);
    }

    public long getPostsCountByPlanId(String planId) {
        return postDao.getPostsCountByPlanId(planId);
    }

    public List<Post> getPagedPostsByPlanId(String planId, int page, int size) {
        return postDao.getPagedPostsByPlanId(planId, page, size);
    }

    public void addMessage(PostMessage message) {
         postMessageDao.create(message);
    }

    public List<Post> getLatestPosts(int limit) {
         return postDao.getLatestPosts(limit);
    }

    public Topic getTopic(String postId) {
         return postDao.getTopic(postId);
    }
}
