package com.tissue.plan.service;

import com.tissue.core.plan.PostMessage;
import com.tissue.core.plan.dao.PostMessageDao;

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
