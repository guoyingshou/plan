package com.tissue.plan.service;

import com.tissue.domain.social.Event;
import com.tissue.domain.plan.QuestionComment;
import com.tissue.plan.dao.QuestionCommentDao;
import com.tissue.commons.dao.social.EventDao;
import com.tissue.commons.util.EventFactory;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class QuestionCommentService {

    @Autowired
    private QuestionCommentDao questionCommentDao;

    @Autowired
    private EventDao eventDao;

    public QuestionComment addQuestionComment(QuestionComment comment) {
        comment = questionCommentDao.create(comment);
        /**
        Event event = EventFactory.createEvent(comment);
        eventDao.addEvent(event);
        */

        return comment;
    }

    public void updateQuestionComment(QuestionComment comment) {
        questionCommentDao.update(comment);
    }

}
