package com.tissue.plan.service;

import com.tissue.domain.plan.PostMessageComment;
import com.tissue.plan.dao.PostMessageCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PostMessageCommentService {

    @Autowired
    private PostMessageCommentDao postMessageCommentDao;

    public PostMessageComment addComment(PostMessageComment comment) {
        return postMessageCommentDao.create(comment);
    }

    public String updateComment(PostMessageComment comment) {
        comment = postMessageCommentDao.update(comment);
        return comment.getId();
    }

    public void deleteComment(String commentId) {
        postMessageCommentDao.delete(commentId);
    }

}
