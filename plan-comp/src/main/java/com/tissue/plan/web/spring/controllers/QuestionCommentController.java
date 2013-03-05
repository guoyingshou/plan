package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.QuestionComment;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.social.services.UserService;
import com.tissue.plan.web.model.QuestionCommentForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.QuestionCommentService;

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
public class QuestionCommentController {

    private static Logger logger = LoggerFactory.getLogger(QuestionCommentController.class);
    @Autowired
    private TopicService topicService;

    @Autowired
    private QuestionCommentService questionCommentService;

    /**
     * Add a comment to a specific question(a kind of post).
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/questionComments/_create", method=POST)
    public String addQuestionComment(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @Valid QuestionCommentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }
        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        Post post = new Post();
        post.setId("#"+postId);
        form.setQuestion(post);
        form.setAccount(viewerAccount);

        questionCommentService.addQuestionComment(form);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }
 
    /**
     * Update a QuestionComment.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/questionComments/{commentId}/_update", method=POST)
    public String updateQuestionComment(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("commentId") String commentId, @Valid QuestionCommentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }
        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setId("#"+commentId);
        questionCommentService.updateQuestionComment(form);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

    /**
     * Delete a QuestionComment.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/questionComments/{commentId}/_delete", method=POST)
    public String deleteQuestionComment(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("commentId") String commentId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        questionCommentService.deleteQuestionComment("#"+commentId);
        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

}
