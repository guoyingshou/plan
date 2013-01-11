package com.tissue.plan.web.spring.controllers;

import com.tissue.core.profile.User;
import com.tissue.core.plan.Answer;
import com.tissue.core.plan.AnswerComment;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.service.AnswerCommentService;

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
public class AnswerCommentAjaxController {

    @Autowired
    protected AnswerCommentService answerCommentService;

    /**
     * Add a comment to the answer of a specific question.
     */
    @RequestMapping(value="/answers/{answerId}/comments", method=POST)
    public String addAnswerComment(@PathVariable("answerId") String answerId, @RequestParam("content") String content, Map model) {

        AnswerComment comment = new AnswerComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        comment.setUser(user);

        Answer answer = new Answer();
        answer.setId(answerId);
        comment.setAnswer(answer);

        comment = answerCommentService.addComment(comment);
        model.put("comment", comment);
        return "fragments/newAnswerComment";
    }

    /**
     * Update an answer comment.
     */
    @RequestMapping(value="/answerComments/{commentId}", method=POST)
    @ResponseBody
    public String updateAnswerComment(@PathVariable("commentId") String commentId, @RequestParam("content") String content, Map model) {

        AnswerComment comment = new AnswerComment();
        comment.setId(commentId);
        comment.setContent(content);
        answerCommentService.updateComment(comment);

        return "ok";
    }

    /**
     * Delete an answer comment.
     */
    @RequestMapping(value="/answerComments/{commentId}/delete", method=POST)
    @ResponseBody
    public String deleteAnswerComment(@PathVariable("commentId") String commentId, Map model) {
        answerCommentService.deleteComment(commentId);
        return "ok";
    }

}
