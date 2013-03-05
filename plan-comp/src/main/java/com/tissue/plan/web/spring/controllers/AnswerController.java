package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.Question;
import com.tissue.core.plan.Answer;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.social.services.UserService;
import com.tissue.plan.web.model.AnswerForm;
import com.tissue.plan.services.AnswerService;
import com.tissue.plan.services.TopicService;

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
public class AnswerController {

    private static Logger logger = LoggerFactory.getLogger(AnswerController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private AnswerService answerService;

    /**
     * Add an answer to a specific post.
     * The post's type can only be 'question'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/answers/_create", method=POST)
    public String addAnswer(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @Valid AnswerForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        Post post = new Post();
        post.setId("#"+postId);
        Question q = new Question(post);
        form.setQuestion(q);
        form.setAccount(viewerAccount);

        Answer answer = answerService.addAnswer(form);
        model.put("answer", answer);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

    /**
     * Update an answer.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/answers/{answerId}/_update", method=POST)
    public String updateAnswer(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("answerId") String answerId, @Valid AnswerForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setId("#"+answerId);
        answerService.updateAnswer(form);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

    /**
     * Delete an answer.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/answers/{answerId}/_delete", method=POST)
    public String deleteAnswer(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("answerId") String answerId, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        answerService.deleteAnswer("#"+answerId);
        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }
}