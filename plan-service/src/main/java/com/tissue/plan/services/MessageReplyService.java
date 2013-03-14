package com.tissue.plan.services;

import com.tissue.core.command.MessageReplyCommand;
import com.tissue.core.plan.MessageReply;
import com.tissue.core.plan.dao.MessageReplyDao;
import com.tissue.core.orient.dao.CommonDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MessageReplyService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private MessageReplyDao messageReplyDao;

    public String addReply(MessageReplyCommand command) {
        return messageReplyDao.create(command);
    }

    public void updateComment(MessageReplyCommand command) {
        messageReplyDao.update(command);
    }

    public void deleteComment(String commentId) {
        commonDao.delete(commentId);
    }

}
