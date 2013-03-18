package com.tissue.plan.services;

import com.tissue.core.command.MessageReplyCommand;
import com.tissue.core.plan.MessageReply;
import com.tissue.core.plan.dao.MessageReplyDao;
import com.tissue.core.plan.dao.PostDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Component
public class MessageReplyService extends ContentService {

    /**
    @Resource(name="postDaoImpl")
    private PostDao postDao;
    */

    @Autowired
    private MessageReplyDao messageReplyDao;

    public String addReply(MessageReplyCommand command) {
        return messageReplyDao.create(command);
    }

    /**
    public void updateMessageReply(MessageReplyCommand command) {
        messageReplyDao.update(command);
    }

    public void deleteMessageReply(String messageReplyId) {
        postDao.delete(messageReplyId);
    }
    */

}
