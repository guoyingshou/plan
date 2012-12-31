package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.domain.profile.User;

import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;

import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;
import com.tissue.domain.plan.QuestionComment;
import com.tissue.domain.plan.Answer;
import com.tissue.domain.plan.AnswerComment;
import com.tissue.plan.service.PostService;
import com.tissue.plan.service.PostMessageService;
import com.tissue.plan.service.PostMessageCommentService;
import com.tissue.plan.service.QuestionCommentService;
import com.tissue.plan.service.AnswerService;
import com.tissue.plan.service.AnswerCommentService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.Map;
import java.util.Date;
import java.security.Principal;

@Controller
public class PostAjaxController {

    @Autowired
    protected PostService postService;

    @Autowired
    protected PostMessageService postMessageService;

    @Autowired
    protected PostMessageCommentService postMessageCommentService;

    @Autowired
    protected QuestionCommentService questionCommentService;

    @Autowired
    protected AnswerService answerService;

    @Autowired
    protected AnswerCommentService answerCommentService;

/**
     * Update a post.
     * The post can be of any type.
     */
    @RequestMapping(value="/posts/{postId}", method=POST)
    @ResponseBody
    public String updatePost(@PathVariable("postId") String postId, PostForm form, Map model) {

        Post post = new Post();
        post.setId(postId);
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setType(form.getType());

        postService.updatePost(post);
        return "ok";
    }

    /**
     * Add a message to a specific post.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/posts/{postId}/messages", method=POST)
    public String addMessage(@PathVariable("postId") String postId, @RequestParam("content") String content, Map model) {

        PostMessage message = new PostMessage();
        message.setContent(content);
        message.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        message.setUser(user);

        Post post = new Post();
        post.setId(postId);
        message.setPost(post);

        PostMessage postMessage = postMessageService.addMessage(message);

        model.put("postMessage", postMessage);
        return "fragments/newMessage";
    }
 
    /**
     * Update a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messages/{msgId}", method=POST)
    @ResponseBody
    public String updateMessage(@PathVariable("msgId") String msgId, @RequestParam("content") String content, Map model) {
        System.out.println("msgId: " + msgId);
        System.out.println("content: " + content);

        PostMessage postMessage = new PostMessage();
        postMessage.setId(msgId);
        postMessage.setContent(content);
        postMessageService.updatePostMessage(postMessage);

        return "ok";
    }

    /**
     * Add a comment to the message of a specific post.
     * The post's type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messages/{msgId}/comments", method=POST)
    public String addMessageComment(@PathVariable("msgId") String msgId, @RequestParam("content") String content, Map model) {

        PostMessageComment comment = new PostMessageComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        comment.setUser(user);

        PostMessage msg = new PostMessage();
        msg.setId(msgId);
        comment.setPostMessage(msg);

        comment = postMessageCommentService.addComment(comment);
        model.put("postMessageComment", comment);
        return "fragments/newPostMessageComment";
    }

    /**
     * Update a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messageComments/{commentId}", method=POST)
    @ResponseBody
    public String updateMessageComment(@PathVariable("commentId") String commentId, @RequestParam("content") String content, Map model) {

        PostMessageComment comment = new PostMessageComment();
        comment.setId(commentId);
        comment.setContent(content);
        postMessageCommentService.updateComment(comment);

        return "ok";
    }

    /**
     * ------------------question ----------------------
     */

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
     * The post type can be 'concept', 'note' or 'tutorial'.
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
     * Add a comment to the answer of a specific question.
     */
    @RequestMapping(value="/answers/{answerId}/comments", method=POST)
    public String addAnswerComment(@PathVariable("answerId") String answerId, @RequestParam("content") String content, Map model) {

        AnswerComment comment = new AnswerComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
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


}
