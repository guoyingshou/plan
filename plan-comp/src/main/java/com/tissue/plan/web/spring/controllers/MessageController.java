package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Article;
import com.tissue.core.plan.Message;
import com.tissue.commons.util.Pager;
import com.tissue.plan.web.model.ContentForm;
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
    private MessageService messageService;

    /**
     * Add a message to a specific post.
     */
    @RequestMapping(value="/articles/{articleId}/messages/_create", method=POST)
    public String addMessage(@PathVariable("articleId") Article article, @Valid MessageForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {
        form.setArticle(article);
        form.setAccount(viewerAccount);
        messageService.addMessage(form);
        return "redirect:/articles/" + article.getId().replace("#", "");
    }
 
    /**
     * Update a message.
     */
    @RequestMapping(value="/messages/{msgId}/_update", method=POST)
    public String updateMessage(@PathVariable("msgId") Message message, @Valid ContentForm form, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {

        form.setId(message.getId());
        messageService.updateContent(form);

        return "redirect:/articles/" + message.getArticle().getId().replace("#", "");
    }

    /**
     * Delete a message.
     */
    @RequestMapping(value="/messages/{msgId}/_delete", method=POST)
    public String deleteMessage(@PathVariable("msgId") Message message, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {
        messageService.deleteContent(message.getId());
        return "redirect:/articles/" + message.getArticle().getId().replace("#", "");
    }

}
