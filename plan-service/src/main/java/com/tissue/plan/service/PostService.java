package com.tissue.plan.service;

import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
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
     * Save a post.
     * 
     * @param post
     * @return the newly added post
     */
    public Post addPost(Post post) {
        return postDao.create(post);
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

    /**
    public List<Post> getPostsByTopicId(String topicId) {
        return postDao.getPostsByTopicId(topicId);
    }
    */

    public long getPostsCountByTopicIdAndType(String topicId, String type) {
        return postDao.getPostsCountByTopicIdAndType(topicId, type);
    }

    public List<Post> getPagedPostsByTopicIdAndType(String topicId, String type, int page, int size) {
        return postDao.getPagedPostsByTopicIdAndType(topicId, type, page, size);
    }

    /**
    public List<Post> getPostsByTopicIdAndType(String topicId, String type) {
        return postDao.getPostsByTopicIdAndType(topicId, type);
    }
    */

    public long getPostsCountByPlanId(String planId) {
        return postDao.getPostsCountByPlanId(planId);
    }

    public List<Post> getPagedPostsByPlanId(String planId, int page, int size) {
        return postDao.getPagedPostsByPlanId(planId, page, size);
    }

    /**
    public List<Post> getPostsByPlanId(String planId) {
        return postDao.getPostsByPlanId(planId);
    }
    */

    public void addMessage(PostMessage message) {
         postMessageDao.create(message);
    }

    //--------- by user

    public long getPostsCountByUserId(String userId) {
        return postDao.getPostsCountByUserId(userId);
    }

    public List<Post> getPagedPostsByUserId(String userId, int page, int size) {
        return postDao.getPagedPostsByUserId(userId, page, size);
    }

    /**
    public List<Post> getPostsByUserId(String userId) {
        return postDao.getPostsByUserId(userId);
    }
    */

}
