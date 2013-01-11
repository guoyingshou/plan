package com.tissue.plan.web.spring.controllers;

import com.tissue.core.profile.User;
import com.tissue.core.plan.Topic;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.service.TopicService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;

@Controller
public class TopicWriteController {

    @Autowired
    private TopicService topicService;

    /**
     * Show topic create form.
    @RequestMapping(value="/topics")
    public String showTopicForm(Locale locale, Map model) {

        String lang = locale.toLanguageTag();
        if(lang != null) 
            model.put("lang", lang);

        model.put("user", SecurityUtil.getViewer());
        return "topicForm";
    }
     */

    /**
     * Add new topic.
     */
    @RequestMapping(value="/topics", method=POST)
    public String addTopic(TopicForm form, Map model) throws Exception {

        Topic topic = new Topic();
        topic.setTitle(form.getTitle());
        topic.setContent(form.getContent());

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        topic.setUser(user);

        Date date = new Date();
        topic.setCreateTime(date);
        topic.setUpdateTime(date);

        String tags = form.getTags();
        String[] splits = tags.split("\\s");
        topic.setTags(new HashSet(Arrays.asList(splits)));

        topic = topicService.addTopic(topic);
        
        return "redirect:/topics/" + topic.getId() + "/posts";
    }

}
