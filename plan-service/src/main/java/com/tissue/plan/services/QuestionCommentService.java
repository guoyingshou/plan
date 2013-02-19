package com.tissue.plan.services;

import com.tissue.core.command.QuestionCommentCommand;
import com.tissue.core.plan.QuestionComment;
import com.tissue.core.plan.dao.QuestionCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class QuestionCommentService {

    @Autowired
    private QuestionCommentDao questionCommentDao;

    public QuestionComment addQuestionComment(QuestionCommentCommand command) {
        return questionCommentDao.create(command);
    }

    public void updateQuestionComment(QuestionCommentCommand command) {
        questionCommentDao.update(command);
    }

    public void deleteQuestionComment(String commentId) {
        questionCommentDao.delete(commentId);
    }
}
