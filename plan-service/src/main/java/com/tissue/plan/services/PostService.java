package com.tissue.plan.services;

//import com.tissue.core.command.ItemCommand;
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

    /**
    @Autowired
    private PostMessageDao postMessageDao;
    */

    /**
     * @param postCommand 
     * @return id of the newly created post
     */
    public String addPost(PostCommand postCommand) {
        return postDao.create(postCommand);
    }

    public void updatePost(PostCommand post) {
        postDao.update(post);
    }

    /**
     * @param postId postId
     * @return topic id of the containing topic of the deleted post
     */
    public void deletePost(String postId) {
        commonDao.delete(postId);
    }

    /**
     * Get a specific post.
    public Post getPost(String postId) {
        return postDao.getPost(postId);
    }

    public void addMessage(PostMessageCommand message) {
         postMessageDao.create(message);
    }
     */

    public List<Post> getLatestPosts(int limit) {
         return postDao.getLatestPosts(limit);
    }

    public Topic getTopic(String postId) {
         return postDao.getTopic(postId);
    }
}
