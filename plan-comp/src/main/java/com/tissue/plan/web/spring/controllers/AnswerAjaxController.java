package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.domain.profile.User;

/**
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;
*/

import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Post;
/**
import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;
import com.tissue.domain.plan.QuestionComment;
*/
import com.tissue.domain.plan.Answer;
/**
import com.tissue.domain.plan.AnswerComment;
import com.tissue.plan.service.PostService;
import com.tissue.plan.service.PostMessageService;
import com.tissue.plan.service.PostMessageCommentService;
import com.tissue.plan.service.QuestionCommentService;
*/
import com.tissue.plan.service.AnswerService;
//import com.tissue.plan.service.AnswerCommentService;

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
public class AnswerAjaxController {

    @Autowired
    protected AnswerService answerService;

    /**
     * Add an answer to a specific post.
     * The post's type can only be 'question'.
     */
    @RequestMapping(value="/posts/{postId}/answers", method=POST)
    public String addAnswer(@PathVariable("postId") String postId, @RequestParam("content") String content, Map model) {

        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        answer.setUser(user);

        Post post = new Post();
        post.setId(postId);
        answer.setQuestion(post);

        answer = answerService.addAnswer(answer);
        
        model.put("answer", answer);
        return "fragments/newAnswer";
    }

    /**
     * Update an answer.
     */
    @RequestMapping(value="/answers/{answerId}", method=POST)
    @ResponseBody
    public String updateAnswer(@PathVariable("answerId") String answerId, @RequestParam("content") String content, Map model) {

        Answer answer = new Answer();
        answer.setId(answerId);
        answer.setContent(content);
        answerService.updateAnswer(answer);

        return "ok";
    }

    /**
     * Delete an answer.
     */
    @RequestMapping(value="/answers/{answerId}/delete", method=POST)
    @ResponseBody
    public String deleteAnswer(@PathVariable("answerId") String answerId, Map model) {
        answerService.deleteAnswer(answerId);
        return "ok";
    }


}
