package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.commons.util.Pager;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.commons.services.ViewerService;
import com.tissue.plan.Topic;
import com.tissue.plan.Answer;
import com.tissue.plan.AnswerComment;
import com.tissue.plan.web.model.ContentForm;
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

    private static String ROLE_NAME = "ROLE_ADMIN";

    @Autowired
    private ViewerService viewerService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private AnswerCommentService answerCommentService;

    /**
     * Add a comment to the answer of a specific question.
     */
    @RequestMapping(value="/answers/{answerId}/comments/_create", method=POST)
    public String addAnswerComment(@PathVariable("answerId") Answer answer, @Valid AnswerCommentForm form, BindingResult result, Map model) {

        Topic topic = answer.getQuestion().getPlan().getTopic();
        Account viewerAccount = viewerService.getViewerAccount();
        viewerService.checkMembership(topic, viewerAccount);

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        form.setAnswer(answer);
        form.setAccount(viewerAccount);
        answerCommentService.addAnswerComment(form);

        model.clear();
        return "redirect:/questions/" + answer.getQuestion().getId().replace("#","");
    }

    /**
     * Delete an answer comment.
     */
    @RequestMapping(value="/answerComments/{commentId}/_delete", method=POST)
    public String deleteAnswerComment(@PathVariable("commentId") AnswerComment answerComment, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        answerComment.checkPermission(viewerAccount, ROLE_NAME);

        Topic topic = answerComment.getAnswer().getQuestion().getPlan().getTopic();
        answerCommentService.deleteContent(answerComment.getId());

        model.clear();
        return "redirect:/questions/" + answerComment.getAnswer().getQuestion().getId().replace("#","");
    }
}
