package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Post;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PostService;

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
public class PostController {

    private static Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostService postService;

    /**
    @RequestMapping(value="/topics/{topicId}/posts/_form")
    public String newPost(@PathVariable("topicId") String topicId, Map model) {
        Topic topic = topicService.getTopic("#"+topicId);
        model.put("topic", topic);
        model.put("post", new PostForm());
        return "postForm";
    }

    @RequestMapping(value="/topics/{topicId}/posts/_create", method=POST)
    public String addPost(@PathVariable("topicId") String topicId, @ModelAttribute("post") @Valid PostForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            return "postForm";
        }
        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setPlan(topic.getActivePlan());
        form.setAccount(viewerAccount);

        String id = postService.addPost(form).replace("#", "");

        if("topic".equals(form.getType())) {
            return "redirect:/topics/" + topicId + "/objective";
        }
        else if("question".equals(form.getType())) {
            return "redirect:/topics/" + topicId + "/questions/" + id;
        }
        return "redirect:/topics/" + topicId + "/articles/" + id;
        
    }

    @RequestMapping(value="/topics/{topicId}/posts/{postId}/_update", method=POST)
    public String updatePost(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @Valid UpdatePostForm form, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }
        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        form.setId("#"+postId);
        postService.updatePost(form);

        return "redirect:/topics/" + topicId + "/posts/" + postId;
    }

    @RequestMapping(value="/topics/{topicId}/posts/{postId}/_delete", method=POST)
    public String deletePost(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic("#"+topicId);
        topicService.checkActivePlan(topic, viewerAccount);

        postService.deletePost("#"+postId);
        return "redirect:/topics/" + topicId + "/posts";
    }
    */
 
}
