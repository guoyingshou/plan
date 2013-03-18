package com.tissue.plan.services;

import com.tissue.core.command.AnswerCommand;
import com.tissue.core.plan.Answer;
import com.tissue.core.plan.dao.AnswerDao;
//import com.tissue.core.plan.dao.PostDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Component
public class AnswerService extends ContentService {

    /**
    @Resource(name="postDaoImpl")
    private PostDao postDao;
    */

    @Autowired
    private AnswerDao answerDao;

    public String addAnswer(AnswerCommand command) {
        return answerDao.create(command);
    }

    /**
    public void updateAnswer(AnswerCommand command) {
        answerDao.update(command);
    }

    public void deleteAnswer(String answerId) {
        postDao.delete(answerId);
    }
    */
}
