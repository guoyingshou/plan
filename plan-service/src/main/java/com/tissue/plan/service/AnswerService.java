package com.tissue.plan.service;

import com.tissue.domain.plan.Answer;
import com.tissue.plan.dao.AnswerDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    public void addAnswer(Answer answer) {
         answerDao.create(answer);
    }

}
