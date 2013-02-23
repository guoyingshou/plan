package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.QuestionComment;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.controllers.AccessController;
import com.tissue.plan.services.QuestionCommentService;
import com.tissue.plan.web.model.QuestionCommentForm;

import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Date;
import javax.validation.Valid;

@Controller
public class QuestionCommentController extends AccessController {

    @Autowired
    protected QuestionCommentService questionCommentService;

    /**
     * Add a comment to a specific question(a kind of post).
     */
    @RequestMapping(value="/posts/{postId}/questionComments/_create", method=POST)
    public String addQuestionComment(@PathVariable("postId") String postId, @Valid QuestionCommentForm form, BindingResult result, Map model, @ModelAttribute("viewer") User viewer) {

        Account account = new Account();
        account.setId(SecurityUtil.getViewerAccountId());
        account.setUser(viewer);
        form.setAccount(account);

        Post post = new Post();
        post.setId("#"+postId);
        form.setQuestion(post);

        QuestionComment comment = questionCommentService.addQuestionComment(form);

        model.put("questionComment", comment);
        return "fragments/newQuestionComment";
    }
 
    /**
     * Update a QuestionComment.
     */
    @RequestMapping(value="/questionComments/{commentId}/_update", method=POST)
    public HttpEntity<?> updateQuestionComment(@PathVariable("commentId") String commentId, @Valid QuestionCommentForm form, BindingResult result, Map model) {

        checkAuthorizations("#"+commentId);

        form.setId("#"+commentId);
        questionCommentService.updateQuestionComment(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a QuestionComment.
     */
    @RequestMapping(value="/questionComments/{commentId}/_delete", method=POST)
    public HttpEntity<?> deleteQuestionComment(@PathVariable("commentId") String commentId, Map model) {

        checkAuthorizations("#"+commentId);

        questionCommentService.deleteQuestionComment("#"+commentId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


}
