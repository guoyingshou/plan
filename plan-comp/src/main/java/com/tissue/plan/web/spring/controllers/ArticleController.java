package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Article;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.ArticleService;

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

    /**
     * Get specific article.
     */
    @RequestMapping(value="/articles/{articleId}")
    public String getPost(@PathVariable("articleId") String articleId, Map model) {

        Article article = articleService.getArticle("#"+articleId);
        model.put("article", article);
        model.put("topic", article.getPlan().getTopic());

        return "articleDetail";
    }

}
