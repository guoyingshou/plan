package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.exceptions.IllegalAccessException;
import com.tissue.commons.services.CommonService;
import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.services.TopicService;

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
    private CommonService commonService;

    @Autowired
    private TopicService topicService;

    /**
     * Add new topic.
     */
    @RequestMapping(value="/topics/_create", method=POST)
    public String addTopic(@Valid TopicForm form, BindingResult result, Map model) throws Exception {
        
        if(result.hasErrors()) {
            throw new InvalidParameterException("content error");
        }

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        form.setUser(user);

        String topicId = topicService.addTopic(form).replace("#", "");
        return "redirect:/topics/" + topicId + "/posts";
    }

    @RequestMapping(value="/topics/{topicId}/_delete")
    public String deleteTopic(@PathVariable("topicId") String topicId) throws Exception {
        
        if(SecurityUtil.getViewer().hasRole("ROLE_ADMIN") || commonService.isOwner(SecurityUtil.getViewerId(), "#"+topicId)) {
            topicService.deleteTopic("#"+topicId);
            return "redirect:/topics";
        }
        else {
            throw new IllegalAccessException("Don't be evil");
        }

   }


}
