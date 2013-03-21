package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.commons.util.Pager;
import com.tissue.social.Activity;
import com.tissue.plan.Topic;
import com.tissue.plan.Post;
import com.tissue.plan.services.ExploreService;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PostService;

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

@Controller
public class ExploreController {

    @Autowired
    protected ExploreService exploreService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostService postService;

    @RequestMapping("/explore")
    public String exploreTrending(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "trending");

        List<Topic> topics = topicService.getTrendingTopics(15);
        model.put("topics", topics);

        List<Post> posts = postService.getLatestPosts(15);
        model.put("posts", posts);

        String viewerId = (viewerAccount == null) ? null : viewerAccount.getUser().getId();
        List<User> users = exploreService.getNewUsers(viewerId, 10);
        model.put("users", users);

        return "explore";
    }

    @RequestMapping("/featured")
    public String exploreFeatured(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "featured");

        List<Topic> topics = topicService.getFeaturedTopics(15);
        model.put("topics", topics);

        List<Post> posts = postService.getLatestPosts(15);
        model.put("posts", posts);

        String viewerId = (viewerAccount == null) ? null : viewerAccount.getUser().getId();
        List<User> users = exploreService.getNewUsers(viewerId, 10);
        model.put("users", users);

        return "explore";
    }

    @RequestMapping("/topics")
    public String exploreTopics(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "topics");

        page = (page == null) ? 1 : page;
        size = (size == null) ? 15 : size;
        long total = topicService.getTopicsCount();
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Topic> topics = topicService.getPagedTopics(page, size);
        model.put("topics", topics);

        List<Post> posts = postService.getLatestPosts(15);
        model.put("posts", posts);

        String viewerId = (viewerAccount == null) ? null : viewerAccount.getUser().getId();
        List<User> users = exploreService.getNewUsers(viewerId, 10);
        model.put("users", users);

        return "explore";
    }

    @RequestMapping("/tags/{tag}")
    public String getTopicsByTag(@PathVariable("tag") String tag, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "tags");

        page = (page == null) ? 1 : page;
        size = (size == null) ? 15 : size;
        long total = topicService.getTopicsCountByTag(tag);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Topic> topics = topicService.getPagedTopicsByTag(tag, page, size);
        model.put("topics", topics);

        List<Post> posts = postService.getLatestPosts(15);
        model.put("posts", posts);

        String viewerId = (viewerAccount == null) ? null : viewerAccount.getUser().getId();
        List<User> users = exploreService.getNewUsers(viewerId, 10);
        model.put("users", users);

        return "explore";
    }

    @RequestMapping("/tags")
    public String exploreTags(Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "tags");

        List<String> tags = topicService.getTopicTags();
        model.put("tags", tags);

        String viewerId = (viewerAccount == null) ? null : viewerAccount.getUser().getId();
        List<User> users = exploreService.getNewUsers(viewerId, 10);
        model.put("users", users);

        return "tags";
    }

}
