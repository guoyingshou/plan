package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
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

    /**
    @Autowired
    private PlanService planService;
    */

    @RequestMapping(value="/topics/{topicId}/articles/_form")
    public String newPost(@PathVariable("topicId") Topic topic, Map model, @ModelAttribute("viewer") Account viewerAccount) {

        model.put("topic", topic);

        topicService.checkMember(topic, viewerAccount, model);

        /**
        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);
        */

        model.put("articleForm", new ArticleForm());
        return "articleFormView";
    }

    @RequestMapping(value="/topics/{topicId}/articles/_create", method=POST)
    public String addQuestion(@PathVariable("topicId") Topic topic, @ModelAttribute("articleForm") @Valid ArticleForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            return "articleFormView";
        }

        topicService.checkMember(topic, viewerAccount, model);

        form.setPlan(topic.getActivePlan());
        form.setAccount(viewerAccount);

        String articleId = articleService.addArticle(form);

        return "redirect:/articles/" + articleId.replace("#","");
        
    }

    /**
     * Get specific article.
     */
    @RequestMapping(value="/articles/{articleId}", method=GET)
    public String getPost(@PathVariable("articleId") Article article, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = article.getPlan().getTopic();

        model.put("article", article);
        model.put("topic", topic);

        topicService.checkMember(topic, viewerAccount, model);

        /**
        Boolean isMember = false;
        Plan plan = article.getPlan().getTopic().getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);
        */

        return "articleDetail";
    }

}
