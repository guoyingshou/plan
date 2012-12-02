package com.tissue.plan.service;

import com.tissue.domain.social.Event;
import com.tissue.domain.plan.PostMessageComment;
import com.tissue.plan.dao.PostMessageCommentDao;
import com.tissue.commons.dao.social.EventDao;
import com.tissue.commons.util.EventFactory;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PostMessageCommentService {

    @Autowired
    private PostMessageCommentDao postMessageCommentDao;

    @Autowired
    private EventDao eventDao;

    public void addComment(PostMessageComment comment) {
        comment = postMessageCommentDao.create(comment);
        Event event = EventFactory.createEvent(comment);
        eventDao.addEvent(event);
    }

}
