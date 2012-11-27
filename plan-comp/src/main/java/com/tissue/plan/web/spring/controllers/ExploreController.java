package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
//import com.tissue.commons.util.PagedDataHolder;
import com.tissue.domain.plan.Topic;
import com.tissue.plan.service.TopicService;

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

    @RequestMapping("/explore")
    public String explore(Map model) {
        model.put("viewer", SecurityUtil.getUser());
        return "explore";
    }

    @RequestMapping("/exploreTopics")
    public String exploreTopics(@RequestParam(value="page", required=false) Integer currentPage, @RequestParam(value="pageSize", required=false) Integer pageSize, Map model) {

        /**
        Integer page = currentPage == null ? 1 : currentPage;
        Integer size = pageSize == null ? 2 : pageSize;

        long count = topicService.getTopicsCount();
        List<Topic> topics = topicService.getTopics(page, size);
        
        PagedDataHolder<Topic> pdh = new PagedDataHolder(page, size, count);
        pdh.setPagedItems(topics);
        model.put("pagedData", pdh);
        */

        List<Topic> topics = topicService.getTopics();
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
        model.put("viewer", SecurityUtil.getUser());
        return "exploreTimeline";
    }

}
