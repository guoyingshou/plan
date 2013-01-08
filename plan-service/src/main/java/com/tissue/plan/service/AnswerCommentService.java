package com.tissue.plan.service;

import com.tissue.domain.plan.AnswerComment;
import com.tissue.plan.dao.AnswerCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerCommentService {

    @Autowired
    private AnswerCommentDao answerCommentDao;

    public AnswerComment addComment(AnswerComment comment) {
        return answerCommentDao.create(comment);
     }

    public void updateComment(AnswerComment comment) {
        answerCommentDao.update(comment);
    }

    public void deleteComment(String commentId) {
        answerCommentDao.delete(commentId);
    }
}
