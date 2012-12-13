package com.tissue.plan.web.spring.controllers;

import com.tissue.domain.social.Event;
import com.tissue.domain.plan.Topic;
import com.tissue.plan.service.TopicService;
import com.tissue.commons.service.EventService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private EventService eventService;

    @RequestMapping("/explore")
    public String explore(Map model) {

        List<Topic> trendingTopics = topicService.getTrendingTopics();
        List<Topic> featuredTopics = topicService.getFeaturedTopics();
        model.put("trendingTopics", trendingTopics);
        model.put("featuredTopics", featuredTopics);
        model.put("viewer", SecurityUtil.getUser());

        return "explore";
    }

    @RequestMapping("/exploreTopics")
    public String exploreTopics(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        long total = topicService.getTopicsCount();
        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;

        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Topic> topics = topicService.getPagedTopics(page, size);
        model.put("topics", topics);
        model.put("viewer", SecurityUtil.getUser());

        return "exploreTopics";
    }

    @RequestMapping("/exploreTags")
    public String exploreTags(Map model) {
        List<String> tags = topicService.getTopicTags();

        model.put("tags", tags);
        model.put("viewer", SecurityUtil.getUser());
        return "exploreTags";
    }

    @RequestMapping("/exploreTags/{tag}")
    public String getTopicsByTag(@PathVariable("tag") String tag, Map model) {

        List<Topic> topics = topicService.getTopicsByTag(tag);
        model.put("topics", topics);
        model.put("viewer", SecurityUtil.getUser());

        return "exploreTopics";
    }

    @RequestMapping("/exploreTimeline")
    public String exploreTimeline(Map model) {
        List<Event> events = eventService.getLatestEvents();
        model.put("events", events);
        model.put("viewer", SecurityUtil.getUser());
        return "exploreTimeline";
    }

}
