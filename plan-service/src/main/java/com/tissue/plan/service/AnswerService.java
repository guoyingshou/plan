package com.tissue.plan.service;

import com.tissue.core.command.AnswerCommand;
import com.tissue.core.plan.Answer;
import com.tissue.core.plan.dao.AnswerDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    public String addAnswer(AnswerCommand answer) {
        return answerDao.create(answer);
    }

    public void updateAnswer(AnswerCommand answer) {
        answerDao.update(answer);
    }

    public void deleteAnswer(String answerId) {
        answerDao.delete(answerId);
    }
}
