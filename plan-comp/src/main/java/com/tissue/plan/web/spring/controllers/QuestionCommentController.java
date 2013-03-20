package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Question;
import com.tissue.core.plan.QuestionComment;
import com.tissue.commons.util.Pager;
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

    @Autowired
    private TopicService topicService;

    @Autowired
    private QuestionCommentService questionCommentService;

    /**
     * Add a comment to a specific question(a kind of post).
     */
    @RequestMapping(value="/questions/{questionId}/questionComments/_create", method=POST)
    public String addQuestionComment(@PathVariable("questionId") Question question, @Valid QuestionCommentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }
        Topic topic = question.getPlan().getTopic();
        topicService.checkMember(topic, viewerAccount, model);

        form.setQuestion(question);
        form.setAccount(viewerAccount);

        questionCommentService.addQuestionComment(form);

        return "redirect:/questions/" + question.getId().replace("#", "");
    }
 
    /**
     * Update a QuestionComment.
     */
    @RequestMapping(value="/questionComments/{commentId}/_update", method=POST)
    public String updateQuestionComment(@PathVariable("commentId") QuestionComment questionComment, @Valid ContentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }
        Topic topic = questionComment.getQuestion().getPlan().getTopic();
        topicService.checkMember(topic, viewerAccount, model);

        form.setId(questionComment.getId());
        questionCommentService.updateContent(form);

        return "redirect:/questions/" + questionComment.getQuestion().getId().replace("#","");
    }

    /**
     * Delete a QuestionComment.
     */
    @RequestMapping(value="/questionComments/{commentId}/_delete", method=POST)
    public String deleteQuestionComment(@PathVariable("commentId") QuestionComment questionComment, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = questionComment.getQuestion().getPlan().getTopic();
        topicService.checkMember(topic, viewerAccount, model);

        questionCommentService.deleteContent(questionComment.getId());
        return "redirect:/questions/" + questionComment.getQuestion().getId().replace("#","");
    }

}
