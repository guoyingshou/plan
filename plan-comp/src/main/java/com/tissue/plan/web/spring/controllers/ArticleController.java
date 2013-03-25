package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Article;
import com.tissue.plan.web.model.PostForm;
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
    private ArticleService articleService;

    @RequestMapping(value="/topics/{topicId}/articles/_create")
    public String newPost(@PathVariable("topicId") Topic topic, Map model, @ModelAttribute("viewer") Account viewerAccount) {

        model.put("selected", "all");

        topicService.checkMember(topic, viewerAccount, model);
        model.put("topic", topic);

        model.put("articleForm", new PostForm());
        return "createArticleFormView";
    }

    @RequestMapping(value="/topics/{topicId}/articles/_create", method=POST)
    public String addQuestion(@PathVariable("topicId") Topic topic, @ModelAttribute("articleForm") @Valid PostForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            model.put("selected", "all");
            model.put("topic", topic);

            topicService.checkMember(topic, viewerAccount, model);
            return "createArticleFormView";
        }

        topicService.checkMember(topic, viewerAccount, model);

        form.setPlan(topic.getActivePlan());
        form.setAccount(viewerAccount);

        String articleId = articleService.createPost(form);

        return "redirect:/articles/" + articleId.replace("#","");
        
    }

    /**
     * Get specific article.
     */
    @RequestMapping(value="/articles/{articleId}", method=GET)
    public String getPost(@PathVariable("articleId") Article article, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", article.getType());

        Topic topic = article.getPlan().getTopic();

        model.put("article", article);
        model.put("topic", topic);

        topicService.checkMember(topic, viewerAccount, model);

        return "articleDetail";
    }

    @RequestMapping(value="/articles/{articleId}/_update")
    public String updateArticleFormView(@PathVariable("articleId") Article article, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", article.getType());

        Topic topic = article.getPlan().getTopic();
        topicService.checkMember(topic, viewerAccount, model);
        model.put("topic", topic);

        model.put("articleForm", article);
        return "updateArticleFormView";

    }

    @RequestMapping(value="/articles/{articleId}/_update", method=POST)
    public String updatePost(@PathVariable("articleId") Article article, @Valid @ModelAttribute("articleForm") PostForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            model.put("selected", article.getType());
            Topic topic = article.getPlan().getTopic();
            topicService.checkMember(topic, viewerAccount, model);
            model.put("topic", topic);

            return "updateArticleFormView";
        }

        form.setId(article.getId());
        articleService.updatePost(form);

        return "redirect:/articles/" + article.getId().replace("#","");
    }

    @RequestMapping(value="/articles/{articleId}/_delete", method=POST)
    public String deletePost(@PathVariable("articleId") Article article, @ModelAttribute("viewerAccount") Account viewerAccount) {
        articleService.deleteContent(article.getId());
        return "redirect:/articles/" + article.getId().replace("#","");
    }

}
