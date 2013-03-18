package com.tissue.plan.services;

import com.tissue.core.command.MessageCommand;
import com.tissue.core.plan.Message;
import com.tissue.core.plan.dao.MessageDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MessageService extends ContentService {

    @Autowired
    private MessageDao messageDao;

    public String addMessage(MessageCommand command) {
        return messageDao.create(command);
    }

    /**
    public void updateMessage(MessageCommand command) {
        messageDao.update(command);
    }

    public void deleteMessage(String messageId) {
        commonDao.delete(messageId);
    }
    */
}