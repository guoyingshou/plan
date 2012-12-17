package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.domain.profile.User;

import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;

import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;
import com.tissue.plan.service.PostService;
import com.tissue.plan.service.PostMessageService;
import com.tissue.plan.service.PostMessageCommentService;

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
    protected PostService postService;

    @Autowired
    protected PostMessageService postMessageService;

    @Autowired
    protected PostMessageCommentService postMessageCommentService;

    /**
     * Add a post to the active plan.
     * The post can be any type.
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
     * Update a post.
     * The post can be of any type.
     */
    @RequestMapping(value="/posts/{postId}", method=POST)
    public String updatePost(@PathVariable("postId") String postId, PostForm form, Map model) {

        Post post = new Post();
        post.setId(postId);
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());

        postService.updatePost(post);

        return "redirect:/plan/posts/" + postId;
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

        postMessageService.addMessage(message);

        return "redirect:/plan/posts/" + postId;
    }

    /**
     * Add a comment to the message of a specific post.
     * The post's type can be 'concept', 'note' or 'tutorial'.
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
     */

}
