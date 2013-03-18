package com.tissue.plan.services;

import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Article;
import com.tissue.core.plan.Message;
import com.tissue.core.plan.dao.TopicDao;
import com.tissue.core.plan.dao.ArticleDao;
import com.tissue.core.plan.dao.MessageDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ArticleService extends PostService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private ArticleDao articleDao;

    /**
     * Get a specific article.
     */
    public Article getArticle(String articleId) {
        return articleDao.getArticle(articleId);
    }

    public Topic getTopic(String articleId) {
        return topicDao.getTopicByPost(articleId);
    }

}
