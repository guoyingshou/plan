package com.tissue.plan.services;

import com.tissue.core.command.ArticleCommand;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Article;
import com.tissue.core.plan.Message;
import com.tissue.core.plan.dao.ArticleDao;
import com.tissue.core.plan.dao.MessageDao;
import com.tissue.core.orient.dao.CommonDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class ArticleService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private MessageDao messageDao;

    public String addArticle(ArticleCommand command) {
        return articleDao.create(command);
    }

    /**
     * Get a specific article.
     */
    public Article getArticle(String articleId) {
        return articleDao.getArticle(articleId);
    }

    public Topic getTopic(String articleId) {
        return articleDao.getTopic(articleId);
    }

    /**
    public void addMessage(PostMessageCommand message) {
         postMessageDao.create(message);
    }

    public List<Post> getLatestPosts(int limit) {
         return postDao.getLatestPosts(limit);
    }

    public Topic getTopic(String postId) {
         return postDao.getTopic(postId);
    }
    */
}
