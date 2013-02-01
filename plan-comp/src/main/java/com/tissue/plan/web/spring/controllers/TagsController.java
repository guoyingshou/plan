package com.tissue.plan.web.spring.controllers;

import com.tissue.core.plan.Topic;
import com.tissue.core.social.User;
import com.tissue.core.social.Activity;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.ViewerUserSetter;
import com.tissue.commons.util.Pager;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.social.service.ActivityService;
import com.tissue.plan.service.TopicService;
import com.tissue.plan.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;

@Controller
public class TagsController extends ViewerUserSetter {

    @Autowired
    private TopicService topicService;

    /**
    @ModelAttribute("users")
    public List<User> getNewUsers() {
        String viewerId = SecurityUtil.getViewerId();
        return userService.getNewUsers(viewerId, 15);
    }
    */

    @RequestMapping("/tags")
    public String exploreTags(Map model) {
        List<String> tags = topicService.getTopicTags();
        model.put("tags", tags);
        return "tags";
    }

}
