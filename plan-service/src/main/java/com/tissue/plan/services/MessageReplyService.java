package com.tissue.plan.services;

import com.tissue.commons.services.ContentService;
import com.tissue.plan.command.MessageReplyCommand;
import com.tissue.plan.MessageReply;
import com.tissue.plan.dao.MessageReplyDao;
import com.tissue.plan.dao.PostDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Component
public class MessageReplyService extends ContentService {

    @Autowired
    private MessageReplyDao messageReplyDao;

    public String addReply(MessageReplyCommand command) {
        return messageReplyDao.create(command);
    }

}
