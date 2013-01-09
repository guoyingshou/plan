package com.tissue.plan.service;

import com.tissue.core.plan.Answer;
import com.tissue.core.plan.dao.AnswerDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    public Answer addAnswer(Answer answer) {
        return answerDao.create(answer);
    }

    public void updateAnswer(Answer answer) {
        answerDao.update(answer);
    }

    public void deleteAnswer(String answerId) {
        answerDao.delete(answerId);
    }
}
