package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.social.User;
import com.tissue.core.plan.Answer;
import com.tissue.core.plan.AnswerComment;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.services.AnswerCommentService;
import com.tissue.plan.web.model.AnswerCommentForm;

import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Date;
import javax.validation.Valid;

@Controller
public class AnswerCommentAjaxController {

    @Autowired
    protected AnswerCommentService answerCommentService;

    /**
     * Add a comment to the answer of a specific question.
     */
    @RequestMapping(value="/answers/{answerId}/comments/_create", method=POST)
    public String addAnswerComment(@PathVariable("answerId") String answerId, @Valid AnswerCommentForm form, BindingResult result, Map model) {

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        form.setUser(user);

        Answer answer = new Answer();
        answer.setId("#"+answerId);
        form.setAnswer(answer);

        AnswerComment comment = answerCommentService.addComment(form);
        model.put("comment", comment);
        return "fragments/newAnswerComment";
    }

    /**
     * Update an answer comment.
     */
    @RequestMapping(value="/answerComments/{commentId}/_update", method=POST)
    public HttpEntity<?> updateAnswerComment(@PathVariable("commentId") String commentId, @Valid AnswerCommentForm form, BindingResult result, Map model) {

        form.setId("#"+commentId);
        answerCommentService.updateComment(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete an answer comment.
     */
    @RequestMapping(value="/answerComments/{commentId}/_delete", method=POST)
    public HttpEntity<?> deleteAnswerComment(@PathVariable("commentId") String commentId) {
        answerCommentService.deleteComment("#"+commentId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
