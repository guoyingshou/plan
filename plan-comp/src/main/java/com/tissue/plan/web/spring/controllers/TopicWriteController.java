package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.service.TopicService;

import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.security.InvalidParameterException;

@Controller
public class TopicWriteController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private ConversionService conversionService;


    /**
     * Add new topic.
     */
    @RequestMapping(value="/topics", method=POST)
    public String addTopic(@Valid TopicForm form, BindingResult result, Map model) throws Exception {
        
        if(result.hasErrors()) {
            throw new InvalidParameterException("content error");
        }

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        form.setUser(user);

        Topic topic = topicService.addTopic(form);
        return "redirect:/topics/" + topic.getId() + "/posts";
    }

}
