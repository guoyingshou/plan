package com.tissue.plan.services;

import com.tissue.core.command.PostMessageCommand;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.plan.dao.PostMessageDao;
import com.tissue.core.orient.dao.CommonDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PostMessageService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private PostMessageDao postMessageDao;

    public PostMessage addMessage(PostMessageCommand message) {
        return postMessageDao.create(message);
    }

    public void updatePostMessage(PostMessageCommand message) {
        postMessageDao.update(message);
    }

    public void deletePostMessage(String messageId) {
        commonDao.delete(messageId);
    }
}
