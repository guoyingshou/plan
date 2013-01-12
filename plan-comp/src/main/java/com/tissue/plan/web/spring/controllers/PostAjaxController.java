package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.service.PostService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class PostAjaxController {

    @Autowired
    protected PostService postService;

    /**
     * Update a post.
     * The post can be of any type.
     */
    @RequestMapping(value="/posts/{postId}", method=POST)
    @ResponseBody
    public String updatePost(@PathVariable("postId") String postId, PostForm form, Map model) {

        Post post = new Post();
        post.setId(postId);
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setType(form.getType());

        postService.updatePost(post);
        return "ok";
    }

}
