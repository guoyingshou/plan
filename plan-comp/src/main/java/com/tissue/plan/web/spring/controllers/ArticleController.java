package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Article;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PostService;
import com.tissue.plan.services.ArticleService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ArticleController {

    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @Autowired
    private ArticleService articleService;

    @ModelAttribute("viewerActivePlansCount")
    private int setupViewerActivePlansCount() {
        return planService.getViewerActivePlansCount(SecurityUtil.getViewerAccountId());
    }

    @RequestMapping(value="/topics/{topicId}/articles/_create")
    public String newPost(@PathVariable("topicId") Topic topic, Map model) {

        model.put("selected", "all");
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, SecurityUtil.getViewerAccountId()));
        model.put("articleForm", new PostForm());

        return "createArticleFormView";
    }

    @RequestMapping(value="/topics/{topicId}/articles/_create", method=POST)
    public String addQuestion(@PathVariable("topicId") Topic topic, @ModelAttribute("articleForm") @Valid PostForm form, BindingResult result, Map model) {

        if(result.hasErrors()) {
            model.put("selected", "all");
            model.put("topic", topic);
            model.put("isMember", topicService.isMember(topic, SecurityUtil.getViewerAccountId()));

            return "createArticleFormView";
        }

        form.setPlan(topic.getActivePlan());
        Account viewerAccount = new Account();
        viewerAccount.setId(SecurityUtil.getViewerAccountId());
        form.setAccount(viewerAccount);

        String articleId = articleService.createPost(form);

        model.clear();
        return "redirect:/articles/" + articleId.replace("#","");
        
    }

    /**
     * Get specific article.
     */
    @RequestMapping(value="/articles/{articleId}", method=GET)
    public String getPost(@PathVariable("articleId") Article article, Map model) {

        model.put("selected", article.getType());

        Topic topic = article.getPlan().getTopic();

        model.put("article", article);
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, SecurityUtil.getViewerAccountId()));

        model.put("messageForm", new MessageForm());

        return "articleDetail";
    }

    @RequestMapping(value="/articles/{articleId}/_update")
    public String updateArticleFormView(@PathVariable("articleId") Article article, Map model) {

        if((article == null) || !article.getAccount().getId().equals(SecurityUtil.getViewerAccountId())) {
            return "accessDenied";
        }

        model.put("selected", article.getType());

        Topic topic = article.getPlan().getTopic();
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, SecurityUtil.getViewerAccountId()));

        model.put("articleForm", article);
        return "updateArticleFormView";

    }

    @RequestMapping(value="/articles/{articleId}/_update", method=POST)
    public String updatePost(@PathVariable("articleId") Article article, @Valid @ModelAttribute("articleForm") PostForm form, BindingResult result, Map model) {

        if((article == null) || !article.getAccount().getId().equals(SecurityUtil.getViewerAccountId())) {
            return "redirect:/accessDenied";
        }

        if(result.hasErrors()) {
            model.put("selected", article.getType());
            Topic topic = article.getPlan().getTopic();
            model.put("topic", topic);
            model.put("isMember", topicService.isMember(topic, SecurityUtil.getViewerAccountId()));

            return "updateArticleFormView";
        }

        form.setId(article.getId());
        articleService.updatePost(form);

        model.clear();
        return "redirect:/articles/" + article.getId().replace("#","");
    }

    @RequestMapping(value="/articles/{articleId}/_delete", method=POST)
    public String deletePost(@PathVariable("articleId") Article article) {

        if((article == null) || !article.getAccount().getId().equals(SecurityUtil.getViewerAccountId())) {
            return "accessDenied";
        }

        articleService.deleteContent(article.getId());
        return "redirect:/articles/" + article.getId().replace("#","");
    }

}
