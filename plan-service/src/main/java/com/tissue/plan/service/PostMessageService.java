package com.tissue.plan.service;

import com.tissue.domain.plan.PostMessage;
import com.tissue.plan.dao.PostMessageDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PostMessageService {

    @Autowired
    private PostMessageDao postMessageDao;

    public void addMessage(PostMessage message) {
         postMessageDao.create(message);
    }

}
