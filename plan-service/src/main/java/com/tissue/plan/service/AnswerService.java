package com.tissue.plan.service;

//import com.tissue.domain.social.Event;
import com.tissue.domain.plan.Answer;
import com.tissue.plan.dao.AnswerDao;
//import com.tissue.commons.dao.social.EventDao;
//import com.tissue.commons.util.EventFactory;


import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    /**
    @Autowired
    private EventDao eventDao;
    */

    public Answer addAnswer(Answer answer) {
        answer = answerDao.create(answer);
        /**
        Event event = EventFactory.createEvent(answer);
        eventDao.addEvent(event);
        */

        return answer;
    }

    public void updateAnswer(Answer answer) {
        answerDao.update(answer);
    }
}
