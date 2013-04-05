package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.plan.Topic;
import com.tissue.plan.Post;
import com.tissue.plan.Message;
import com.tissue.plan.MessageReply;
import com.tissue.plan.web.model.ContentForm;
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
    @RequestMapping(value="/messages/{messageId}/messageReplies/_create", method=POST)
    public String addMessageReply(@PathVariable("messageId") Message message, @Valid MessageReplyForm form, BindingResult resutl, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        /**
        Topic topic = message.getArticle().getPlan().getTopic();
        topicService.checkMember(topic, viewerAccount, model);
        */

        form.setMessage(message);
        form.setAccount(viewerAccount);
        messageReplyService.addReply(form);

        return "redirect:/articles/" + message.getArticle().getId().replace("#","");
    }

    /**
     * Update a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
    @RequestMapping(value="/messageReplies/{replyId}/_update", method=POST)
    public String updateMessageReply(@PathVariable("replyId") MessageReply messageReply, @Valid ContentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        form.setId(messageReply.getId());
        messageReplyService.updateContent(form);

        return "redirect:/articles/" + messageReply.getMessage().getArticle().getId().replace("#", "");
    }
     */

    /**
     * Delete a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messageReplies/{replyId}/_delete", method=POST)
    public String deleteMessageReply(@PathVariable("replyId") MessageReply messageReply, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        /**
        Topic topic = messageReply.getMessage().getArticle().getPlan().getTopic();
        topicService.checkMember(topic, viewerAccount, model);
        */

        messageReplyService.deleteContent(messageReply.getId());
        return "redirect:/articles/" + messageReply.getMessage().getArticle().getId().replace("#", "");
    }

}
