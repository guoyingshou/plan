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
public class PostAjaxController {

    @Autowired
    protected PostService postService;

    @Autowired
    protected PostMessageService postMessageService;

    @Autowired
    protected PostMessageCommentService postMessageCommentService;

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

        model.put("post", post);

        return "fragments/updatePost";
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
    public String updateMessage(@PathVariable("msgId") String msgId, @RequestParam("content") String content, Map model) {
        System.out.println("msgId: " + msgId);
        System.out.println("content: " + content);

        PostMessage postMessage = new PostMessage();
        postMessage.setId(msgId);
        postMessage.setContent(content);
        postMessageService.updatePostMessage(postMessage);

        model.put("postMessage", postMessage);

        return "fragments/updateMessage";
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
    public String updateMessageComment(@PathVariable("commentId") String commentId, @RequestParam("content") String content, Map model) {

        PostMessageComment comment = new PostMessageComment();
        comment.setId(commentId);
        comment.setContent(content);
        postMessageCommentService.updateComment(comment);

        model.put("postMessageComment", comment);
        return "fragments/updatePostMessageComment";
    }

}
