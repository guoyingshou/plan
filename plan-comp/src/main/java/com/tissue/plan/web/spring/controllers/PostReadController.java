package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Post;
import com.tissue.plan.service.TopicService;
import com.tissue.plan.service.PostService;

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
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class PostReadController {

    @Autowired
    protected TopicService topicService;

    @Autowired
    protected PostService postService;

    /**
     * Get specific post.
     */
    @RequestMapping(value="/posts/{postId}")
    public String getPost(@PathVariable("postId") String postId, Map model, Locale locale) {
        String viewerId = SecurityUtil.getUserId();
        if(viewerId != null) {
             model.put("viewer", SecurityUtil.getUser());
        }

        String lang = locale.toLanguageTag();
        if(lang != null) 
            model.put("lang", lang);

        Post post = postService.getPost(postId);
        model.put("post", post);

        String topicId = post.getPlan().getTopic().getId();
        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);
        
        if("question".equals(post.getType())) {
            return "questionDetail";
        }
        return "postDetail";
    }

}