package com.tissue.plan.service;

import com.tissue.domain.plan.QuestionComment;
import com.tissue.plan.dao.QuestionCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class QuestionCommentService {

    @Autowired
    private QuestionCommentDao questionCommentDao;

    public QuestionComment addQuestionComment(QuestionComment comment) {
        return questionCommentDao.create(comment);
    }

    public void updateQuestionComment(QuestionComment comment) {
        questionCommentDao.update(comment);
    }

    public void deleteQuestionComment(String commentId) {
        questionCommentDao.delete(commentId);
    }
}
