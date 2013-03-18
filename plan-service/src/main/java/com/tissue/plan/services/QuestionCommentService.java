package com.tissue.plan.services;

import com.tissue.core.command.QuestionCommentCommand;
import com.tissue.core.plan.QuestionComment;
import com.tissue.core.plan.dao.QuestionCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class QuestionCommentService extends ContentService {

    @Autowired
    private QuestionCommentDao questionCommentDao;

    public String addQuestionComment(QuestionCommentCommand command) {
        return questionCommentDao.create(command);
    }

}
