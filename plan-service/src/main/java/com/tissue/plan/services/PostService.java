package com.tissue.plan.services;

import com.tissue.core.command.PostCommand;
//import com.tissue.core.command.PostMessageCommand;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.Message;
import com.tissue.core.plan.dao.PostDao;
import com.tissue.core.plan.dao.MessageDao;
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

    public void deletePost(String postId) {
        commonDao.delete(postId);
    }

    public List<Post> getLatestPosts(int limit) {
         return postDao.getLatestPosts(limit);
    }

}
