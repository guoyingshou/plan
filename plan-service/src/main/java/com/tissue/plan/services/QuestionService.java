package com.tissue.plan.services;

import com.tissue.core.plan.Question;
import com.tissue.core.plan.dao.QuestionDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class QuestionService extends PostService {

    @Autowired
    private QuestionDao questionDao;

    public Question getQuestion(String questionId) {
        return questionDao.getQuestion(questionId);
    }

}
