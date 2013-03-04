package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.plan.PostMessageComment;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.social.services.UserService;
import com.tissue.plan.web.model.PostMessageCommentForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PostMessageCommentService;

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
public class PostMessageCommentController {

    private static Logger logger = LoggerFactory.getLogger(PostMessageCommentController.class);
    @Autowired
    private TopicService topicService;

    @Autowired
    private PostMessageCommentService postMessageCommentService;

    /**
     * Add a comment to the message of a specific post.
     * The post's type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/{messageId}/comments/_create", method=POST)
    public String addMessageComment(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("messageId") String messageId, @Valid PostMessageCommentForm form, BindingResult resutl, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        PostMessage message = new PostMessage();
        message.setId("#"+messageId);
        form.setPostMessage(message);
        form.setAccount(viewerAccount);

        PostMessageComment comment = postMessageCommentService.addComment(form);
        model.put("messageComment", comment);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
        //return "fragments/newMessageComment";
    }

    /**
     * Update a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/{messageId}/messageComments/{commentId}/_update", method=POST)
    public String updateMessageComment(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("messageId") String messageId, @PathVariable("commentId") String commentId, @Valid PostMessageCommentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setId("#"+commentId);
        postMessageCommentService.updateComment(form);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
        //return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/{messageId}/messageComments/{commentId}/_delete", method=POST)
    public String deleteMessageComment(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("messageId") String messageId, @PathVariable("commentId") String commentId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        postMessageCommentService.deleteComment("#"+commentId);
        return "redirect:/topics/" + topicId + "/posts/" + postId;
        //return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
