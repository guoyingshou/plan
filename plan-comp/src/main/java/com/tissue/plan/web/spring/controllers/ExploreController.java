package com.tissue.plan.web.spring.controllers;

import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
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
public class ExploreController extends ViewerUserSetter {

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostService postService;

    @Autowired
    private ActivityService activityService;

    /**
    @ModelAttribute("users")
    public List<User> getNewUsers() {
        String viewerId = SecurityUtil.getViewerId();
        return userService.getNewUsers(viewerId, 15);
    }
    */

    @ModelAttribute("newPosts")
    public List<Post> getNewPosts() {
        return postService.getLatestPosts(15);
    }

    @RequestMapping("/explore")
    public String exploreTrending(Map model) {

        model.put("current", "trending");

        List<Topic> topics = topicService.getTrendingTopics(15);
        model.put("topics", topics);

        return "topics";
    }

    @RequestMapping("/featured")
    public String exploreFeatured(Map model) {

        model.put("current", "featured");

        List<Topic> topics = topicService.getFeaturedTopics(15);
        model.put("topics", topics);

        return "topics";
    }

    @RequestMapping("/topics")
    public String exploreTopics(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        model.put("current", "topics");

        page = (page == null) ? 1 : page;
        size = (size == null) ? 15 : size;
        long total = topicService.getTopicsCount();
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Topic> topics = topicService.getPagedTopics(page, size);
        model.put("topics", topics);

        return "topics";
    }

    @RequestMapping("/tags/{tag}")
    public String getTopicsByTag(@PathVariable("tag") String tag, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        model.put("current", "tags");

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
