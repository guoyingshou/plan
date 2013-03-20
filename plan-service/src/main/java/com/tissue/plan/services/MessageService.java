package com.tissue.plan.services;

import com.tissue.commons.services.ContentService;
import com.tissue.plan.command.MessageCommand;
import com.tissue.plan.Message;
import com.tissue.plan.dao.MessageDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MessageService extends ContentService {

    @Autowired
    private MessageDao messageDao;

    public String addMessage(MessageCommand command) {
        return messageDao.create(command);
    }
}
