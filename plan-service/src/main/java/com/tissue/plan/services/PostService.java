package com.tissue.plan.services;

import com.tissue.commons.services.ContentService;
import com.tissue.plan.command.PostCommand;
import com.tissue.plan.Post;
import com.tissue.plan.dao.PostDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import javax.annotation.Resource;

@Component
public class PostService extends ContentService {

    @Resource(name="postDaoImpl")
    private PostDao postDao;

    public String createPost(PostCommand command) {
        return postDao.create(command);
    }

    public void updatePost(PostCommand command) {
        postDao.update(command);
    }

    public List<Post> getLatestPosts(int limit) {
         return postDao.getLatestPosts(limit);
    }

}
