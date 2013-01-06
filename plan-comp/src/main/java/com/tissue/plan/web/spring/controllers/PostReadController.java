package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.security.core.userdetails.UserDetailsImpl;
import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Post;
import com.tissue.plan.service.TopicService;
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
public class PostReadController {

    @Autowired
    protected TopicService topicService;

    @Autowired
    protected PostService postService;

    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }

    @ModelAttribute("viewer")
    public UserDetailsImpl prefetchViewer() {
        return SecurityUtil.getUser();
    }
 
    @ModelAttribute("post")
    public Post prefetchPost(@PathVariable("postId") String postId, Map model) {
        Post post = postService.getPost(postId);
        String topicId = post.getPlan().getTopic().getId();
        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);
        return post;
    }

    /**
     * Get specific post.
     */
    @RequestMapping(value="/posts/{postId}")
    public String getPost(@PathVariable("postId") String postId, @ModelAttribute("post") Post post, Map model, Locale locale) {

        if("question".equals(post.getType())) {
            return "questionDetail";
        }
        return "postDetail";
    }

    /**
     * show post edit form.
    @RequestMapping(value="/posts/{postId}/edit")
    public String showPostEditForm(@PathVariable("postId") String postId, Map model, Locale locale) {
        return "postEditForm";
    }
     */

}
