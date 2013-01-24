package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.service.PostMessageService;

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
public class PostMessageAjaxController {

    @Autowired
    protected PostMessageService postMessageService;

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
        user.setId(SecurityUtil.getViewerId());
        user.setDisplayName(SecurityUtil.getDisplayName());
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

        PostMessage postMessage = new PostMessage();
        postMessage.setId(msgId);
        postMessage.setContent(content);
        postMessageService.updatePostMessage(postMessage);

        return "ok";
    }

    /**
     * Delete a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messages/{msgId}/delete", method=POST)
    @ResponseBody
    public String deleteMessage(@PathVariable("msgId") String msgId, Map model) {
        postMessageService.deletePostMessage(msgId);
        return "ok";
    }


}
