package com.tissue.plan.service;

import com.tissue.domain.profile.User;
import com.tissue.domain.plan.Question;
import com.tissue.plan.dao.QuestionDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Component
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    /**
    @Autowired
    private PostMessageDao postMessageDao;
    */

    /**
     * Save a question.
     * 
     * @param question
     * @return the newly added question
     */
    public Question addQuestion(Question question) {
        question = questionDao.create(question);
        return question;
    }
}
