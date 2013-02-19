package com.tissue.plan.services;

import com.tissue.core.command.AnswerCommand;
import com.tissue.core.plan.Answer;
import com.tissue.core.plan.dao.AnswerDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    public Answer addAnswer(AnswerCommand command) {
        return answerDao.create(command);
    }

    public void updateAnswer(AnswerCommand command) {
        answerDao.update(command);
    }

    public void deleteAnswer(String answerId) {
        answerDao.delete(answerId);
    }
}
