package com.tissue.plan.service;

import com.tissue.core.command.PostMessageCommentCommand;
import com.tissue.core.plan.PostMessageComment;
import com.tissue.core.plan.dao.PostMessageCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PostMessageCommentService {

    @Autowired
    private PostMessageCommentDao postMessageCommentDao;

    public PostMessageComment addComment(PostMessageCommentCommand command) {
        return postMessageCommentDao.create(command);
    }

    public void updateComment(PostMessageCommentCommand command) {
        postMessageCommentDao.update(command);
    }

    public void deleteComment(String commentId) {
        postMessageCommentDao.delete(commentId);
    }

}
