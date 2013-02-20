package com.tissue.plan.services;

import com.tissue.core.command.ItemCommand;
import com.tissue.core.command.PostCommand;
import com.tissue.core.command.PostMessageCommand;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.plan.dao.PostDao;
import com.tissue.core.plan.dao.PostMessageDao;
import com.tissue.core.orient.dao.CommonDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class PostService {

    @Autowired
    private CommonDao commonDao;

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

    public void updatePost(PostCommand post) {
        postDao.update(post);
    }

    /**
     * @param postId postId
     * @return topic id of the containing topic of the deleted post
     */
    public String deletePost(ItemCommand command) {
        String topicId = postDao.getTopicId(command.getId());
        commonDao.delete(command);
        return topicId.replace("#", "");
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

    public void addMessage(PostMessageCommand message) {
         postMessageDao.create(message);
    }

    public List<Post> getLatestPosts(int limit) {
         return postDao.getLatestPosts(limit);
    }

    public Topic getTopic(String postId) {
         return postDao.getTopic(postId);
    }
}
