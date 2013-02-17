package com.tissue.plan.web.spring.controllers;

import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.ViewerSetter;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.service.PostService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class PostReadController extends ViewerSetter {

    @Autowired
    protected PostService postService;

    /**
     * Get specific post.
     */
    @RequestMapping(value="/posts/{postId}")
    public String getPost(@PathVariable("postId") String postId, Map model) {

        postId = "#" + postId;

        Topic topic = postService.getTopic(postId);
        model.put("topic", topic);

        Post post = postService.getPost(postId);
        model.put("post", post);

        if("question".equals(post.getType())) {
            return "questionDetail";
        }
        return "postDetail";
    }

}
