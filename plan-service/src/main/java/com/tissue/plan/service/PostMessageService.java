package com.tissue.plan.service;

import com.tissue.core.command.PostMessageCommand;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.plan.dao.PostMessageDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PostMessageService {

    @Autowired
    private PostMessageDao postMessageDao;

    public PostMessage addMessage(PostMessageCommand message) {
        return postMessageDao.create(message);
    }

    public void updatePostMessage(PostMessageCommand message) {
        postMessageDao.update(message);
    }

    public void deletePostMessage(String messageId) {
        postMessageDao.delete(messageId);
    }
}
