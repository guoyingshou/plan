package com.tissue.plan.services;

import com.tissue.plan.command.QuestionCommentCommand;
import com.tissue.plan.QuestionComment;
import com.tissue.plan.dao.QuestionCommentDao;
import com.tissue.commons.services.ContentService;

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
