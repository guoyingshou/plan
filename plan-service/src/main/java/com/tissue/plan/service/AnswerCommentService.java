package com.tissue.plan.service;

import com.tissue.domain.social.Event;
import com.tissue.domain.plan.AnswerComment;
import com.tissue.plan.dao.AnswerCommentDao;
import com.tissue.commons.dao.social.EventDao;
import com.tissue.commons.util.EventFactory;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AnswerCommentService {

    @Autowired
    private AnswerCommentDao answerCommentDao;

    @Autowired
    private EventDao eventDao;

    public AnswerComment addComment(AnswerComment comment) {
        comment = answerCommentDao.create(comment);
        /**
        Event event = EventFactory.createEvent(comment);
        eventDao.addEvent(event);
        */
        return comment;
     }

    public void updateComment(AnswerComment comment) {
        answerCommentDao.update(comment);
    }
}
