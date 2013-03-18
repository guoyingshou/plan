package com.tissue.plan.services;

import com.tissue.core.command.ContentCommand;
import com.tissue.core.plan.dao.ContentDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Component
public class ContentService {

    @Resource(name="contentDaoImpl")
    private ContentDao contentDao;

    public void updateContent(ContentCommand command) {
        contentDao.update(command);
    }

    public void deleteContent(String rid) {
        contentDao.delete(rid);
    }
}
