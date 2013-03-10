package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
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
    public String addMessage(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @Valid PostMessageForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        Post post = new Post();
        post.setId("#"+postId);
        form.setPost(post);
        form.setAccount(viewerAccount);

        PostMessage postMessage = postMessageService.addMessage(form);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }
 
    /**
     * Update a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/{msgId}/_update", method=POST)
    public String updateMessage(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("msgId") String msgId, @Valid PostMessageForm form, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setId("#"+msgId);
        postMessageService.updatePostMessage(form);
        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

    /**
     * Delete a message.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/{msgId}/_delete", method=POST)
    public String deleteMessage(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("msgId") String msgId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        postMessageService.deletePostMessage("#"+msgId);
        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

}
