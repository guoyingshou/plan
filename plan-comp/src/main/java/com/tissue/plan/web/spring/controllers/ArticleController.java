package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Article;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.web.model.ArticleForm;
import com.tissue.plan.services.TopicService;
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
    private ArticleService articleService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @RequestMapping(value="/topics/{topicId}/articles/_form")
    public String newPost(@PathVariable("topicId") String topicId, Map model, @ModelAttribute("viewer") Account viewerAccount) {
        Topic topic = topicService.getTopic("#"+topicId);
        model.put("topic", topic);

        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);

        model.put("articleForm", new ArticleForm());
        return "articleFormView";
    }

    @RequestMapping(value="/topics/{topicId}/articles/_create", method=POST)
    public String addQuestion(@PathVariable("topicId") String topicId, @ModelAttribute("articleForm") @Valid ArticleForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            return "articleFormView";
        }
        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setPlan(topic.getActivePlan());
        form.setAccount(viewerAccount);

        String id = articleService.addArticle(form).replace("#", "");

        return "redirect:/articles/" + id;
        
    }

    /**
     * Get specific article.
     */
    @RequestMapping(value="/articles/{articleId}")
    public String getPost(@PathVariable("articleId") String articleId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Article article = articleService.getArticle("#"+articleId);
        model.put("article", article);

        Topic topic = articleService.getTopic("#"+articleId);
        model.put("topic", topic);

        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);

        return "articleDetail";
    }

}
