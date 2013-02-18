package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.commons.services.CommonService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.service.PostMessageService;
import com.tissue.plan.web.model.PostMessageForm;

import org.springframework.validation.BindingResult;
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
import javax.validation.Valid;

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
    @RequestMapping(value="/posts/{postId}/messages/_create", method=POST)
    public String addMessage(@PathVariable("postId") String postId, @Valid PostMessageForm form, BindingResult result, Map model) {

        postId = "#" + postId;

        String viewerId = SecurityUtil.getViewerId();
        if(!commonService.isMemberOrOwner(viewerId, postId)) {
            throw new InvalidParameterException("not member or owner");
        }

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        form.setUser(user);

        Post post = new Post();
        post.setId(postId);
        form.setPost(post);

        PostMessage postMessage = postMessageService.addMessage(form);
        model.put("postMessage", postMessage);
        return "fragments/newMessage";
    }
 
    /**
     * Update a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messages/{msgId}/_update", method=POST)
    public HttpEntity<?> updateMessage(@PathVariable("msgId") String msgId, @Valid PostMessageForm form, BindingResult result) {

        String viewerId = SecurityUtil.getViewerId();
        if(!commonService.isOwner(viewerId, "#"+msgId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        form.setId("#"+msgId);
        postMessageService.updatePostMessage(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messages/{msgId}/_delete", method=POST)
    public HttpEntity<?> deleteMessage(@PathVariable("msgId") String msgId, Map model) {

        String viewerId = SecurityUtil.getViewerId();
        if(!commonService.isOwner(viewerId, "#"+msgId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        postMessageService.deletePostMessage("#"+msgId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
