package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.commons.services.CommonService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.PostEditForm;
import com.tissue.plan.service.PostService;

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
public class PostAjaxController {

    @Autowired
    protected CommonService commonService;

    @Autowired
    protected PostService postService;

    /**
     * Update a post.
     * The post can be of any type.
     */
    @RequestMapping(value="/posts/{postId}/update", method=POST)
    public HttpEntity<?> updatePost(@PathVariable("postId") String postId, @Valid PostEditForm form, BindingResult result, Map model) {

        String viewerId = SecurityUtil.getViewerId();
        
        if(result.hasErrors() || !commonService.isOwner(viewerId, postId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Post post = new Post();
        post.setId(postId);
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        //post.setType(form.getType());

        postService.updatePost(post);
        return HttpEntity.EMPTY;
    }

}
