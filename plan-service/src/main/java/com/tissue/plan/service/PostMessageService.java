package com.tissue.plan.service;

//import com.tissue.domain.social.Event;
import com.tissue.domain.plan.PostMessage;
import com.tissue.plan.dao.PostMessageDao;
//import com.tissue.commons.dao.social.EventDao;
//import com.tissue.commons.util.EventFactory;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PostMessageService {

    @Autowired
    private PostMessageDao postMessageDao;

    /**
    @Autowired
    private EventDao eventDao;
    */

    public PostMessage addMessage(PostMessage message) {
        message = postMessageDao.create(message);
        /**
        Event event = EventFactory.createEvent(message);
        eventDao.addEvent(event);
        */

        return message;
        //return message.getId();
    }

    public void updatePostMessage(PostMessage message) {
        postMessageDao.update(message);
    }
}
