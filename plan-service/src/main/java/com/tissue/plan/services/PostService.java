package com.tissue.plan.services;

import com.tissue.core.dao.CommonDao;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.dao.PostDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class PostService {

    @Autowired
    private CommonDao commonDao;

    @Resource(name="postDaoImpl")
    private PostDao postDao;

    public void deletePost(String postId) {
        commonDao.delete(postId);
    }

    public List<Post> getLatestPosts(int limit) {
         return postDao.getLatestPosts(limit);
    }

}
