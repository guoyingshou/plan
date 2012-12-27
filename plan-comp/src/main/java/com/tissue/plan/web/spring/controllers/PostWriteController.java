package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.domain.profile.User;

import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;

import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;
import com.tissue.plan.service.PostService;
import com.tissue.plan.service.PostMessageService;
import com.tissue.plan.service.PostMessageCommentService;

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
public class PostWriteController {

    @Autowired
    protected PostService postService;

    @Autowired
    protected PostMessageService postMessageService;

    @Autowired
    protected PostMessageCommentService postMessageCommentService;


    /**
     * Add a post to the active plan.
     * The post can be any type.
     */
    @RequestMapping(value="/plans/{planId}/posts", method=POST)
    public String addPost(@PathVariable("planId") String planId, PostForm form, Map model) {

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setType(form.getType());

        post.setCreateTime(new Date());

        Plan plan = new Plan();
        plan.setId(planId);

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        user.setDisplayName(SecurityUtil.getDisplayName());

        post.setPlan(plan);
        post.setUser(user);

        post = postService.addPost(post);

        return "redirect:/posts/" + post.getId();
    }

}
