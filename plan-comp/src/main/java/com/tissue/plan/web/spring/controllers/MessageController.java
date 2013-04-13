package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.commons.services.ViewerService;
import com.tissue.plan.Topic;
import com.tissue.plan.Article;
import com.tissue.plan.Message;
import com.tissue.plan.web.model.ContentForm;
import com.tissue.plan.web.model.MessageForm;
import com.tissue.plan.services.MessageService;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PlanService;

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
import org.springframework.security.access.AccessDeniedException;

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
    private ViewerService viewerService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @Autowired
    private MessageService messageService;

    @ModelAttribute("viewerActivePlansCount")
    private int setupViewerActivePlansCount() {
        return planService.getViewerActivePlansCount(SecurityUtil.getViewerAccountId());
    }

    /**
     * Add a message to a specific post.
     */
    @RequestMapping(value="/articles/{articleId}/messages/_create", method=POST)
    public String addMessage(@PathVariable("articleId") Article article, @Valid MessageForm form, BindingResult result, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        if(result.hasErrors()) {
            model.put("selected", article.getType());
            Topic topic = article.getPlan().getTopic();

            model.put("article", article);
            model.put("topic", topic);
            model.put("isMember", topicService.isMember(topic, viewerAccount.getId()));

            return "articleDetail";
        }

        form.setArticle(article);
        form.setAccount(viewerAccount);

        messageService.addMessage(form);

        model.clear();
        return "redirect:/articles/" + article.getId().replace("#", "");
    }
 
    /**
     * Delete a message.
     */
    @RequestMapping(value="/messages/{msgId}/_delete", method=POST)
    public String deleteMessage(@PathVariable("msgId") Message message, Map model) {

        String viewerAccountId = SecurityUtil.getViewerAccountId();
        if(!message.getAccount().getId().equals(viewerAccountId)) {
            throw new AccessDeniedException("resource: " + message.getId() + ", user: " + viewerAccountId);
        }

        messageService.deleteContent(message.getId());
        
        return "redirect:/articles/" + message.getArticle().getId().replace("#", "");
    }

}
