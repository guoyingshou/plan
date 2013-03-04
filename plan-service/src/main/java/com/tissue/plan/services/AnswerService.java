package com.tissue.plan.services;

import com.tissue.core.command.AnswerCommand;
import com.tissue.core.plan.Answer;
import com.tissue.core.plan.dao.AnswerDao;
import com.tissue.core.orient.dao.CommonDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private AnswerDao answerDao;

    public Answer addAnswer(AnswerCommand command) {
        return answerDao.create(command);
    }

    public void updateAnswer(AnswerCommand command) {
        answerDao.update(command);
    }

    public void deleteAnswer(String answerId) {
        commonDao.delete(answerId);
    }
}
