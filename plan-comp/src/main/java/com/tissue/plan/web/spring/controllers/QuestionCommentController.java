package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.commons.services.ViewerService;
import com.tissue.plan.Topic;
import com.tissue.plan.Question;
import com.tissue.plan.QuestionComment;
import com.tissue.plan.web.model.ContentForm;
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

    private static String ROLE_NAME = "ROLE_ADMIN";

    @Autowired
    private ViewerService viewerService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private QuestionCommentService questionCommentService;

    /**
     * Add a comment to a specific question(a kind of post).
     */
    @RequestMapping(value="/questions/{questionId}/questionComments/_create", method=POST)
    public String addQuestionComment(@PathVariable("questionId") Question question, @Valid QuestionCommentForm form, BindingResult result, Map model) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        Account viewerAccount = viewerService.getViewerAccount();
        topicService.checkMembership(question.getPlan().getTopic(), viewerAccount);

        form.setQuestion(question);
        form.setAccount(viewerAccount);

        questionCommentService.addQuestionComment(form);

        model.clear();
        return "redirect:/questions/" + question.getId().replace("#", "");
    }
 
    /**
     * Delete a QuestionComment.
     */
    @RequestMapping(value="/questionComments/{commentId}/_delete", method=POST)
    public String deleteQuestionComment(@PathVariable("commentId") QuestionComment questionComment, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        questionComment.checkPermission(viewerAccount, ROLE_NAME);
        //viewerService.checkOwnership(questionComment, viewerAccount);

        questionCommentService.deleteContent(questionComment.getId());

        model.clear();
        return "redirect:/questions/" + questionComment.getQuestion().getId().replace("#","");
    }

}
