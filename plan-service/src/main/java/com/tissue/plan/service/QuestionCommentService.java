package com.tissue.plan.service;

import com.tissue.core.command.QuestionCommentCommand;
import com.tissue.core.plan.QuestionComment;
import com.tissue.core.plan.dao.QuestionCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class QuestionCommentService {

    @Autowired
    private QuestionCommentDao questionCommentDao;

    public String addQuestionComment(QuestionCommentCommand comment) {
        return questionCommentDao.create(comment);
    }

    public void updateQuestionComment(QuestionCommentCommand comment) {
        questionCommentDao.update(comment);
    }

    public void deleteQuestionComment(String commentId) {
        questionCommentDao.delete(commentId);
    }
}
