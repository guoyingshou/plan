package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.command.Command;
import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.commons.exceptions.IllegalAccessException;
import com.tissue.commons.controllers.AccessController;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.web.model.TopicForm;

import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;
import javax.validation.Valid;

@Controller
public class TopicWriteController extends AccessController {

    @Autowired
    private TopicService topicService;

    /**
     * Add new topic.
     */
    @RequestMapping(value="/topics/_create", method=POST)
    public String addTopic(@Valid TopicForm form, BindingResult result, Map model) throws Exception {
        
        if(result.hasErrors()) {
            throw new IllegalAccessException("Don't be evil");
        }

        Account account = new Account();
        account.setId(SecurityUtil.getViewerAccountId());
        form.setAccount(account);

        String topicId = topicService.addTopic(form).replace("#", "");
        return "redirect:/topics/" + topicId + "/posts";
    }

    /**
     * Update topic.
     */
    @RequestMapping(value="/topics/{topicId}/_update", method=POST)
    public HttpEntity<?> updateTopic(@PathVariable("topicId") String topicId, @Valid TopicForm form, BindingResult result, Map model) throws Exception {

        if(result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        checkExistence("#"+topicId);
        checkAuthorizations("#"+topicId);
 
        form.setId("#"+topicId);
        topicService.updateTopic(form);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/topics/{topicId}/_delete", method=POST)
    public String deleteTopic(@PathVariable("topicId") String topicId, @Valid Command command, BindingResult result) {
        
        checkAuthorizations("#"+topicId);

        command.setId("#"+topicId);

        Account account = new Account();
        account.setId(SecurityUtil.getViewerAccountId());
        command.setAccount(account);

        topicService.deleteTopic(command);

        return "redirect:/topics";
 
    }

}
