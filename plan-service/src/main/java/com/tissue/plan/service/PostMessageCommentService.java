package com.tissue.plan.service;

import com.tissue.domain.plan.PostMessageComment;
import com.tissue.plan.dao.PostMessageCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PostMessageCommentService {

    @Autowired
    private PostMessageCommentDao postMessageCommentDao;

    public void addComment(PostMessageComment comment) {
         postMessageCommentDao.create(comment);
    }

}
