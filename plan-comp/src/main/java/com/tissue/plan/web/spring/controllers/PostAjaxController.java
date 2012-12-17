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
     * Update a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messages/{msgId}", method=POST)
    @ResponseBody
    public String addMessage(@PathVariable("msgId") String msgId, @RequestParam("content") String content, Map model) {
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
    @ResponseBody
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

        postMessageCommentService.addComment(comment);
        return "ok";
    }

}
