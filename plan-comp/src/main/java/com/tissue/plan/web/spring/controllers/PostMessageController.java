package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.social.services.UserService;
import com.tissue.plan.web.model.PostMessageForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PostMessageService;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class PostMessageController {

    private static Logger logger = LoggerFactory.getLogger(PostMessageController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostMessageService postMessageService;

    /**
     * Add a message to a specific post.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/_create", method=POST)
    public String addMessage(@PathVariable("postId") String postId, @Valid PostMessageForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        postId = "#" + postId;

        /**
        String viewerAccountId = SecurityUtil.getViewerAccountId();
        if(!commonService.isMemberOrOwner(viewerAccountId, postId)) {
            throw new InvalidParameterException("not member or owner");
        }
        */

        Post post = new Post();
        post.setId(postId);
        form.setPost(post);
        form.setAccount(viewerAccount);

        PostMessage postMessage = postMessageService.addMessage(form);
        model.put("postMessage", postMessage);
        return "fragments/newMessage";
    }
 
    /**
     * Update a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/messages/{msgId}/_update", method=POST)
    public HttpEntity<?> updateMessage(@PathVariable("msgId") String msgId, @Valid PostMessageForm form, BindingResult result) {

        //checkAuthorizations("#"+msgId);

        /**
        String viewerAccountId = SecurityUtil.getViewerAccountId();
        if(!commonService.isOwner(viewerAccountId, "#"+msgId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        */

        form.setId("#"+msgId);
        postMessageService.updatePostMessage(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/messages/{msgId}/_delete", method=POST)
    public HttpEntity<?> deleteMessage(@PathVariable("msgId") String msgId, Map model) {

        //checkAuthorizations("#"+msgId);

        postMessageService.deletePostMessage("#"+msgId);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
