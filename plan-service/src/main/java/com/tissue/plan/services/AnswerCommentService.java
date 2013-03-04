package com.tissue.plan.services;

import com.tissue.core.command.AnswerCommentCommand;
import com.tissue.core.plan.AnswerComment;
import com.tissue.core.plan.dao.AnswerCommentDao;
import com.tissue.core.orient.dao.CommonDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerCommentService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AnswerCommentDao answerCommentDao;

    public AnswerComment addComment(AnswerCommentCommand command) {
        return answerCommentDao.create(command);
     }

    public void updateComment(AnswerCommentCommand command) {
        answerCommentDao.update(command);
    }

    public void deleteComment(String commentId) {
        commonDao.delete(commentId);
    }
}
