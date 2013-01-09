package com.tissue.plan.web.spring.controllers;

import com.tissue.core.plan.Topic;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
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
public class ExploreController {

    @Autowired
    private TopicService topicService;

    /**
    @Autowired
    private EventService eventService;
    */

    @ModelAttribute("viewer")
    public UserDetailsImpl prefetchViewer() {
        return SecurityUtil.getUser();
    }

    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }

    @RequestMapping("/explore")
    public String explore(Map model) {

        List<Topic> trendingTopics = topicService.getTrendingTopics(15);
        List<Topic> featuredTopics = topicService.getFeaturedTopics(15);
        model.put("trendingTopics", trendingTopics);
        model.put("featuredTopics", featuredTopics);

        return "explore";
    }

    @RequestMapping("/exploreTopics")
    public String exploreTopics(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        page = (page == null) ? 1 : page;
        size = (size == null) ? 15 : size;
        long total = topicService.getTopicsCount();
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Topic> topics = topicService.getPagedTopics(page, size);
        model.put("topics", topics);

        return "exploreTopics";
    }

    @RequestMapping("/exploreTags")
    public String exploreTags(Map model) {
        List<String> tags = topicService.getTopicTags();
        model.put("tags", tags);
        return "exploreTags";
    }

    @RequestMapping("/exploreTags/{tag}")
    public String getTopicsByTag(@PathVariable("tag") String tag, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        page = (page == null) ? 1 : page;
        size = (size == null) ? 15 : size;
        long total = topicService.getTopicsCountByTag(tag);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Topic> topics = topicService.getPagedTopicsByTag(tag, page, size);
        model.put("topics", topics);

        return "exploreTopics";
    }

    @RequestMapping("/exploreTimeline")
    public String exploreTimeline(Map model) {
        //List<Event> events = eventService.getLatestEvents(25);
        //model.put("events", events);
        return "exploreTimeline";
    }

}
