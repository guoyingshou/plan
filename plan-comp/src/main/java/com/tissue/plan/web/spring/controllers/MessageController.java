package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Article;
import com.tissue.core.plan.Message;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.web.model.MessageForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.MessageService;

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
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private MessageService messageService;

    /**
     * Add a message to a specific post.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/articles/{articleId}/messages/_create", method=POST)
    public String addMessage(@PathVariable("topicId") String topicId, @PathVariable("articleId") String articleId, @Valid MessageForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        Article article = new Article();
        article.setId("#"+article);
        form.setArticle(article);
        form.setAccount(viewerAccount);

        messageService.addMessage(form);

        return "redirect:/topics/" + topicId + "/posts/" + articleId;
    }
 
    /**
     * Update a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
    @RequestMapping(value="/topics/{topicId}/articles/{articleId}/messages/{msgId}/_update", method=POST)
    public String updateMessage(@PathVariable("topicId") String topicId, @PathVariable("articleId") String articleId, @PathVariable("msgId") String msgId, @Valid MessageForm form, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setId("#"+msgId);
        //messageService.updateMessage(form);
        return "redirect:/topics/" + topicId + "/posts/" + articleId;
    }
     */

    /**
     * Delete a message.
    @RequestMapping(value="/topics/{topicId}/articles/{articleId}/messages/{msgId}/_delete", method=POST)
    public String deleteMessage(@PathVariable("topicId") String topicId, @PathVariable("articleId") String articleId, @PathVariable("msgId") String msgId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        //messageService.deleteMessage("#"+msgId);
        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }
     */

}
