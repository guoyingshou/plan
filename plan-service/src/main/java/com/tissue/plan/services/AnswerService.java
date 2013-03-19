package com.tissue.plan.services;

import com.tissue.core.command.AnswerCommand;
import com.tissue.core.plan.Answer;
import com.tissue.core.plan.dao.AnswerDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Component
public class AnswerService extends ContentService {

    @Autowired
    private AnswerDao answerDao;

    public String addAnswer(AnswerCommand command) {
        return answerDao.create(command);
    }

}
