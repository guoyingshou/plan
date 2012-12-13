package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.domain.profile.User;

import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;
import com.tissue.plan.web.model.AnswerForm;
import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;
import com.tissue.domain.plan.QuestionComment;
import com.tissue.domain.plan.Answer;
import com.tissue.domain.plan.AnswerComment;
import com.tissue.plan.service.TopicService;
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
public class PostWriteController {

    @Autowired
    protected TopicService topicService;

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
     * Add a post to the active plan.
     */
    @RequestMapping(value="/plans/{planId}/posts", method=POST)
    public String addPost(@PathVariable("planId") String planId, PostForm form, Map model) {

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setType(form.getType());

        post.setCreateTime(new Date());

        Plan plan = new Plan();
        plan.setId(planId);

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        user.setDisplayName(SecurityUtil.getDisplayName());

        post.setPlan(plan);
        post.setUser(user);

        post = postService.addPost(post);

        return "redirect:/plan/posts/" + post.getId();
    }

    /**
     * Add a message to a specific post.
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

        postMessageService.addMessage(message);

        return "redirect:/plan/posts/" + postId;
    }

    @RequestMapping(value="/posts/{postId}/messages/{msgId}/comments", method=POST)
    public String addMessageComment(@PathVariable("postId") String postId, @PathVariable("msgId") String msgId, @RequestParam("content") String content, Map model) {

        PostMessageComment comment = new PostMessageComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        comment.setUser(user);

        PostMessage msg = new PostMessage();
        msg.setId(msgId);
        comment.setPostMessage(msg);

        postMessageCommentService.addComment(comment);

        return "redirect:/plan/posts/" + postId;
    }

    /**
     * Add a comment to a specific question.
     */
    @RequestMapping(value="/questions/{postId}/comments", method=POST)
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

        questionCommentService.addComment(comment);

        return "redirect:/plan/posts/" + postId;
    }

    /**
     * Add an answer to a specific post.
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

        answerService.addAnswer(answer);

        return "redirect:/plan/posts/" + postId;
    }

    @RequestMapping(value="/posts/{postId}/answers/{answerId}/comments", method=POST)
    public String addAnswerComment(@PathVariable("postId") String postId, @PathVariable("answerId") String answerId, @RequestParam("content") String content, Map model) {

        AnswerComment comment = new AnswerComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        comment.setUser(user);

        Answer answer = new Answer();
        answer.setId(answerId);
        comment.setAnswer(answer);

        answerCommentService.addComment(comment);

        return "redirect:/plan/posts/" + postId;
    }

}
