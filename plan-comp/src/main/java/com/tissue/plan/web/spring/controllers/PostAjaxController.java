package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.domain.profile.User;

import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;
import com.tissue.plan.web.model.AnswerForm;
import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;
import com.tissue.domain.plan.QuestionComment;
import com.tissue.domain.plan.Answer;
import com.tissue.domain.plan.AnswerComment;
import com.tissue.plan.service.PostService;
import com.tissue.plan.service.PostMessageService;
import com.tissue.plan.service.PostMessageCommentService;
import com.tissue.plan.service.QuestionCommentService;
import com.tissue.plan.service.AnswerService;
import com.tissue.plan.service.AnswerCommentService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.Map;
import java.util.Date;
import java.security.Principal;

@Controller
public class PostAjaxController {

    @Autowired
    protected PostService postService;

    @Autowired
    protected PostMessageService postMessageService;

    @Autowired
    protected PostMessageCommentService postMessageCommentService;

    @Autowired
    protected QuestionCommentService questionCommentService;

    @Autowired
    protected AnswerService answerService;

    @Autowired
    protected AnswerCommentService answerCommentService;

    /**
     * Show form for adding a new post.
     */
    @RequestMapping(value="/plans/{planId}/posts")
    public String showPostCreateForm(@PathVariable("planId") String planId, Locale locale, Map model) {

        model.put("planId", planId);
        model.put("viewer", SecurityUtil.getUser());

        String lang = locale.toLanguageTag();
        if(lang != null) 
            model.put("lang", lang);

        return "fragments/postForm";
    }
 
}
