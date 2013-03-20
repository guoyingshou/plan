package com.tissue.plan.services;

import com.tissue.core.plan.command.MessageReplyCommand;
import com.tissue.core.plan.MessageReply;
import com.tissue.core.plan.dao.MessageReplyDao;
import com.tissue.core.plan.dao.PostDao;
import com.tissue.commons.services.ContentService;

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
