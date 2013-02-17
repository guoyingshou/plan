package com.tissue.plan.service;

import com.tissue.core.command.AnswerCommentCommand;
import com.tissue.core.plan.AnswerComment;
import com.tissue.core.plan.dao.AnswerCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerCommentService {

    @Autowired
    private AnswerCommentDao answerCommentDao;

    public String addComment(AnswerCommentCommand comment) {
        return answerCommentDao.create(comment);
     }

    public void updateComment(AnswerCommentCommand comment) {
        answerCommentDao.update(comment);
    }

    public void deleteComment(String commentId) {
        answerCommentDao.delete(commentId);
    }
}
