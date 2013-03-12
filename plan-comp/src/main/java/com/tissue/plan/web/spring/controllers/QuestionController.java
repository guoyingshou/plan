package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Question;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.web.model.QuestionForm;
//import com.tissue.plan.web.model.UpdatePostForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.QuestionService;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class QuestionController {

    private static Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value="/topics/{topicId}/questions/_form")
    public String newPost(@PathVariable("topicId") String topicId, Map model) {
        Topic topic = topicService.getTopic("#"+topicId);
        model.put("topic", topic);
        model.put("question", new QuestionForm());
        return "questionForm";
    }

    /**
     * Add a post to the active plan.
     * The post can be any type.
     */
    @RequestMapping(value="/topics/{topicId}/questions/_create", method=POST)
    public String addPost(@PathVariable("topicId") String topicId, @ModelAttribute("question") @Valid QuestionForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            return "questionForm";
        }
        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setPlan(topic.getActivePlan());
        form.setAccount(viewerAccount);

        String id = questionService.addQuestion(form).replace("#", "");
        logger.debug("question created with id: " + id);
        return "redirect:/topics/" + topicId + "/questions/" + id;
    }

    /**
     * Get specific post.
     */
    @RequestMapping(value="/topics/{topicId}/questions/{questionId}")
    public String getPost(@PathVariable("topicId") String topicId, @PathVariable("questionId") String questionId, Map model) {

        Topic topic = topicService.getTopic("#"+topicId);
        model.put("topic", topic);

        Question question = questionService.getQuestion("#"+questionId);
        model.put("question", question);

        return "questionDetail";
    }


    /**
     * Update a post.
     * The post can be of any type.
    @RequestMapping(value="/topics/{topicId}/questions/{questionId}/_update", method=POST)
    public String updatePost(@PathVariable("topicId") String topicId, @PathVariable("questionId") String questionId, @Valid UpdatePostForm form, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }
        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setId("#"+questionId);
        questionService.updateQuestion(form);

        return "redirect:/topics/" + topicId + "/questions/" + questionId;
    }

    @RequestMapping(value="/topics/{topicId}/questions/{questionId}/_delete", method=POST)
    public String deletePost(@PathVariable("topicId") String topicId, @PathVariable("questionId") String postId, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        questionService.deleteQuestion("#"+questionId);
        return "redirect:/topics/" + topicId + "/posts";
    }
     */
 
}
