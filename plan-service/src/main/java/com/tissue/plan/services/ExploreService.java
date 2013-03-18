package com.tissue.plan.services;

import com.tissue.core.User;
import com.tissue.core.dao.UserDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.List;

@Component
public class ExploreService {

    @Autowired
    private UserDao userDao;

    public List<User> getNewUsers(String excludingUserId, int limit) {
        return userDao.getNewUsers(excludingUserId, limit);
    }
 
}
