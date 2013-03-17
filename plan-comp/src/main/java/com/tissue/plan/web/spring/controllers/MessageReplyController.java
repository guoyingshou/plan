package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.Message;
import com.tissue.core.plan.MessageReply;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.web.model.MessageReplyForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.MessageReplyService;

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
public class MessageReplyController {

    private static Logger logger = LoggerFactory.getLogger(MessageReplyController.class);
    @Autowired
    private TopicService topicService;

    @Autowired
    private MessageReplyService messageReplyService;

    /**
     * Add a comment to the message of a specific post.
     * The post's type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/{messageId}/comments/_create", method=POST)
    public String addMessageReply(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("messageId") String messageId, @Valid MessageReplyForm form, BindingResult resutl, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        Message message = new Message();
        message.setId("#"+messageId);
        form.setMessage(message);
        form.setAccount(viewerAccount);

        messageReplyService.addReply(form);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

    /**
     * Update a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/{messageId}/messageComments/{commentId}/_update", method=POST)
    public String updateMessageReply(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("messageId") String messageId, @PathVariable("commentId") String commentId, @Valid MessageReplyForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setId("#"+commentId);
        //messageReplyService.updateComment(form);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

    /**
     * Delete a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/{messageId}/messageComments/{commentId}/_delete", method=POST)
    public String deleteMessageReply(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @PathVariable("messageId") String messageId, @PathVariable("commentId") String commentId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        //messageReplyService.deleteComment("#"+commentId);
        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

}
