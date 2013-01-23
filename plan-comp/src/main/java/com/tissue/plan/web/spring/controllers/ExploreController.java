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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.Map;
import java.util.Date;
import java.security.Principal;

@Controller
public class ExploreController extends ViewerSetter {

    @Autowired
    private TopicService topicService;

    @Autowired
    private ActivityService activityService;

    @ModelAttribute("users")
    public List<User> getNewUsers() {
        String viewerId = SecurityUtil.getViewerId();
        if(viewerId == null) {
            return userService.getNewUsers();
        }
        else {
            return userService.getNewUsers(viewerId);
        }
    }

    @RequestMapping("/explore")
    public String exploreTrending(Map model) {
        List<Topic> topics = topicService.getTrendingTopics(15);
        model.put("topics", topics);

        return "trending";
    }

    @RequestMapping("/featured")
    public String exploreFeatured(Map model) {
        List<Topic> topics = topicService.getFeaturedTopics(15);
        model.put("topics", topics);
        return "featured";
    }

    @RequestMapping("/topics")
    public String exploreTopics(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        page = (page == null) ? 1 : page;
        size = (size == null) ? 15 : size;
        long total = topicService.getTopicsCount();
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Topic> topics = topicService.getPagedTopics(page, size);
        model.put("topics", topics);

        return "topics";
    }

    @RequestMapping("/tags")
    public String exploreTags(Map model) {
        List<String> tags = topicService.getTopicTags();
        model.put("tags", tags);
        return "tags";
    }

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

}
