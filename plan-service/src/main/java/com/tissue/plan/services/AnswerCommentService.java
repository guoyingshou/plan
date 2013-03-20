package com.tissue.plan.services;

import com.tissue.commons.services.ContentService;
import com.tissue.core.plan.command.AnswerCommentCommand;
import com.tissue.core.plan.AnswerComment;
import com.tissue.core.plan.dao.AnswerCommentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerCommentService extends ContentService {

    @Autowired
    private AnswerCommentDao answerCommentDao;

    public String addAnswerComment(AnswerCommentCommand command) {
        return answerCommentDao.create(command);
    }

}
