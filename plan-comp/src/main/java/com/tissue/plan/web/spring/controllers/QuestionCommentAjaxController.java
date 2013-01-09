package com.tissue.plan.web.spring.controllers;

import com.tissue.core.profile.User;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.QuestionComment;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.service.QuestionCommentService;

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

@Controller
public class QuestionCommentAjaxController {

    @Autowired
    protected QuestionCommentService questionCommentService;

    /**
     * Add a comment to a specific question(a kind of post).
     */
    @RequestMapping(value="/posts/{postId}/questionComments", method=POST)
    public String addQuestionComment(@PathVariable("postId") String postId, @RequestParam("content") String content, Map model) {

        QuestionComment comment = new QuestionComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        comment.setUser(user);

        Post post = new Post();
        post.setId(postId);
        comment.setQuestion(post);

        comment = questionCommentService.addQuestionComment(comment);

        model.put("questionComment", comment);
        return "fragments/newQuestionComment";
    }
 
    /**
     * Update a QuestionComment.
     */
    @RequestMapping(value="/questionComments/{commentId}", method=POST)
    @ResponseBody
    public String updateQuestionComment(@PathVariable("commentId") String commentId, @RequestParam("content") String content, Map model) {

        QuestionComment comment = new QuestionComment();
        comment.setId(commentId);
        comment.setContent(content);
        questionCommentService.updateQuestionComment(comment);

        return "ok";
    }

    /**
     * Delete a QuestionComment.
     */
    @RequestMapping(value="/questionComments/{commentId}/delete", method=POST)
    @ResponseBody
    public String deleteQuestionComment(@PathVariable("commentId") String commentId, Map model) {
        questionCommentService.deleteQuestionComment(commentId);
        return "ok";
    }


}
