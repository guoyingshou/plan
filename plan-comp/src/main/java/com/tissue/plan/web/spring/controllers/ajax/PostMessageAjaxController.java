package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.commons.services.CommonService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.service.PostMessageService;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Date;

@Controller
public class PostMessageAjaxController {

    @Autowired
    protected CommonService commonService;

    @Autowired
    protected PostMessageService postMessageService;

    /**
     * Add a message to a specific post.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/posts/{postId}/messages", method=POST)
    public String addMessage(@PathVariable("postId") String postId, @RequestParam("content") String content, Map model) {

        String viewerId = SecurityUtil.getViewerId();
        if(!commonService.isMemberOrOwner(viewerId, postId)) {
            throw new InvalidParameterException("not member or owner");
        }

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
    public HttpEntity<?> updateMessage(@PathVariable("msgId") String msgId, @RequestParam("content") String content, Map model) {

        String viewerId = SecurityUtil.getViewerId();
        if(!commonService.isOwner(viewerId, msgId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        PostMessage postMessage = new PostMessage();
        postMessage.setId(msgId);
        postMessage.setContent(content);
        postMessageService.updatePostMessage(postMessage);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messages/{msgId}/delete", method=POST)
    public HttpEntity<?> deleteMessage(@PathVariable("msgId") String msgId, Map model) {

        String viewerId = SecurityUtil.getViewerId();
        if(!commonService.isOwner(viewerId, msgId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        postMessageService.deletePostMessage(msgId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
