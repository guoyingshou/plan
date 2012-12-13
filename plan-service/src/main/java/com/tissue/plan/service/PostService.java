package com.tissue.plan.service;

import com.tissue.domain.social.Event;
import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.PostMessage;
import com.tissue.plan.dao.PostDao;
import com.tissue.plan.dao.PostMessageDao;
import com.tissue.commons.dao.social.EventDao;
import com.tissue.commons.util.EventFactory;

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

    @Autowired
    private EventDao eventDao;

    /**
     * Save a post.
     * 
     * @param post
     * @return the newly added post
     */
    public Post addPost(Post post) {
        post = postDao.create(post);

        //generate event
        Event event = EventFactory.createEvent(post);
        eventDao.addEvent(event);

        return post;
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

    public List<Post> getPostsByTopicId(String topicId) {
        return postDao.getPostsByTopicId(topicId);
    }

    public long getPostsCountByTopicIdAndType(String topicId, String type) {
        return postDao.getPostsCountByTopicIdAndType(topicId, type);
    }

    public List<Post> getPagedPostsByTopicIdAndType(String topicId, String type, int page, int size) {
        return postDao.getPagedPostsByTopicIdAndType(topicId, type, page, size);
    }

    public List<Post> getPostsByTopicIdAndType(String topicId, String type) {
        return postDao.getPostsByTopicIdAndType(topicId, type);
    }

    public long getPostsCountByPlanId(String planId) {
        return postDao.getPostsCountByPlanId(planId);
    }

    public List<Post> getPagedPostsByPlanId(String planId, int page, int size) {
        return postDao.getPagedPostsByPlanId(planId, page, size);
    }

    public List<Post> getPostsByPlanId(String planId) {
        return postDao.getPostsByPlanId(planId);
    }

    public void addMessage(PostMessage message) {
         postMessageDao.create(message);
    }

    //--------- by user

    public List<Post> getPostsByUserId(String userId) {
        return postDao.getPostsByUserId(userId);
    }








    /**
    public long getPostsCountByTopicId(String topicId) {
        return postDao.getPostsCountByTopicId(topicId);
    }

    public List<Post> getPagedPostsByTopicId(String topicId, int page, int pageSize) {
        return postDao.getPagedPostsByTopicId(topicId, page, pageSize);
    }

    public long getPostsCountByPlanId(String planId) {
        return postDao.getPostsCountByPlanId(planId);
    }

    public long getPostsCountByTopicIdAndType(String topicId, String type) {
        return postDao.getPostsCountByTopicIdAndType(topicId, type);
    }

    public long getPostsCountByPlanIdAndType(String planId, String type) {
        return postDao.getPostsCountByPlanIdAndType(planId, type);
    }

    public List<Post> getPagedPostsByPlanId(String planId, int page, int pageSize) {
        return postDao.getPagedPostsByPlanId(planId, page, pageSize);
    }

    public List<Post> getPagedPostsByTopicIdAndType(String topicId, String type, int page, int pageSize) {
        return postDao.getPagedPostsByTopicIdAndType(topicId, type, page, pageSize);
    }

    public List<Post> getPagedPostsByPlanIdAndType(String planId, String type, int page, int pageSize) {
        return postDao.getPagedPostsByPlanIdAndType(planId, type, page, pageSize);
    }
    */

    /**
     * Get posts of a specific topic.
    public List<Post> getPostsByTopicId(String topicId) {
        return postDao.getPostsByTopicId(topicId);
    }

    public List<Post> getPostsByTopicIdAndType(String topicId, String type) {
        return postDao.getPostsByTopicIdAndType(topicId, type);
    }

    public List<Post> getPostsByPlanIdAndType(String planId, String type) {
        return postDao.getPostsByPlanIdAndType(planId, type);
    }
     */

    //----------------------------------
    /**
    public Post addMessageComment(String messageId, Comment comment) {
         return postDao.addMessageComment(messageId, comment);
    }

    public Post addQuestionComment(String postId, Comment comment) {
         return postDao.addQuestionComment(postId, comment);
    }

    public Post addAnswer(String postId, Answer answer) {
         return postDao.addAnswer(postId, answer);
    }

    public Post addAnswerComment(String answerId, Comment comment) {
         return postDao.addAnswerComment(answerId, comment);
    }
    */

    /**
    public Post updateComment(Comment comment) {
        //return postDao.updateComment(comment);
        return null;
    }

    public Post updateAnswer(Answer answer) {
        return postDao.updateAnswer(answer);
    }

    public Post getPost(String postId, String expectedCategory) {
        return postDao.getPost(postId, expectedCategory);
    }

    public Post updatePost(Post post) {
        return postDao.updatePost(post);
    }
    */
}
