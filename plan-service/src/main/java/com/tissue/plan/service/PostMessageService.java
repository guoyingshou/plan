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

    public PostMessage addMessage(PostMessage message) {
        return postMessageDao.create(message);
    }

    public void updatePostMessage(PostMessage message) {
        postMessageDao.update(message);
    }

    public void deletePostMessage(String messageId) {
        postMessageDao.delete(messageId);
    }
}
