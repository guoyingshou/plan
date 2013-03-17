package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Answer;
import com.tissue.core.plan.AnswerComment;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.web.model.AnswerCommentForm;
import com.tissue.plan.services.AnswerCommentService;
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
public class AnswerCommentController {

    private static Logger logger = LoggerFactory.getLogger(AnswerCommentController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private AnswerCommentService answerCommentService;

    /**
     * Add a comment to the answer of a specific question.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/answers/{answerId}/comments/_create", method=POST)
    public String addAnswerComment(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("answerId") String answerId, @Valid AnswerCommentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }
        Topic topic = topicService.getTopic("#" + topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        Answer answer = new Answer();
        answer.setId("#"+answerId);
        form.setAnswer(answer);
        form.setAccount(viewerAccount);

        answerCommentService.addComment(form);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

    /**
     * Update an answer comment.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/answers/{answerId}/comments/{commentId}/_update", method=POST)
    public String updateAnswerComment(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("answerId") String answerId, @PathVariable("commentId") String commentId, @Valid AnswerCommentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }
        Topic topic = topicService.getTopic("#" + topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setId("#"+commentId);
        answerCommentService.updateComment(form);
        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

    /**
     * Delete an answer comment.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/answers/{answerId}/comments/{commentId}/_delete", method=POST)
    public String deleteAnswerComment(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("commentId") String commentId, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#" + topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        answerCommentService.deleteComment("#"+commentId);
        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }
}
