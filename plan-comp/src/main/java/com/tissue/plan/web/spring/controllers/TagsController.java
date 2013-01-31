package com.tissue.plan.web.spring.controllers;

import com.tissue.core.plan.Topic;
import com.tissue.core.social.User;
import com.tissue.core.social.Activity;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.ViewerSetter;
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
public class TagsController extends ViewerSetter {

    @Autowired
    private TopicService topicService;

    @ModelAttribute("users")
    public List<User> getNewUsers() {
        String viewerId = SecurityUtil.getViewerId();
        return userService.getNewUsers(viewerId, 15);
    }

    @RequestMapping("/tags")
    public String exploreTags(Map model) {
        List<String> tags = topicService.getTopicTags();
        model.put("tags", tags);
        return "tags";
    }

    /**
    @RequestMapping("/tags/{tag}")
    public String getTopicsByTag(@PathVariable("tag") String tag, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        page = (page == null) ? 1 : page;
        size = (size == null) ? 15 : size;
        long total = topicService.getTopicsCountByTag(tag);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Topic> topics = topicService.getPagedTopicsByTag(tag, page, size);
        model.put("topics", topics);

        return "topics";
    }
    */

}