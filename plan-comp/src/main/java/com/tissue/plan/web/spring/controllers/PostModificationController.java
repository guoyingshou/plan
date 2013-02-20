package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.commons.controllers.AccessController;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.services.PostService;

import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class PostModificationController extends AccessController {

    @Autowired
    protected PostService postService;

    /**
     * Update a post.
     * The post can be of any type.
     */
    @RequestMapping(value="/posts/{postId}/_update", method=POST)
    public HttpEntity<?> updatePost(@PathVariable("postId") String postId, @Valid PostForm form, BindingResult result) {

        checkAuthorizations("#"+postId);
        /**
        String viewerId = SecurityUtil.getViewerId();
        
        if(result.hasErrors() || !commonService.isOwner(viewerId, "#"+postId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        */

        form.setId("#"+postId);
        postService.updatePost(form);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/posts/{postId}/_delete", method=POST)
    public String deletePost(@PathVariable("postId") String postId) {

        checkAuthorizations("#"+postId);

        String topicId = postService.deletePost("#"+postId);
        return "redirect:/topics/" + topicId + "/posts";
    }
 
}