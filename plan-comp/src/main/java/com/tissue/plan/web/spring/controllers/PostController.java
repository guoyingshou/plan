package com.tissue.plan.web.spring.controllers;

import com.tissue.core.command.Command;
import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.social.services.UserService;
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

    @RequestMapping(value="/topics/{topicId}/posts/_form")
    public String newPost(@PathVariable("topicId") String topicId, Map model) {

        topicId = "#" + topicId;

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);


        //todo: authorization check
        return "postForm";
    }
 
    /**
     * Get specific post.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}")
    public String getPost(@PathVariable("topicId") String topicId, @PathVariable("postId") String postId, Map model) {

        topicId = "#" + topicId;
        postId = "#" + postId;

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);

        Post post = postService.getPost(postId);
        model.put("post", post);

        if("question".equals(post.getType())) {
            return "questionDetail";
        }
        return "topic";
    }

    /**
     * Add a post to the active plan.
     * The post can be any type.
     */
    @RequestMapping(value="/topics/{topicId}/posts/_create", method=POST)
    public String addPost(@PathVariable("topicId") String topicId, @Valid PostForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount, @ModelAttribute("topic") Topic topic) {

        if(result.hasErrors()) {
            //throw new IllegalAccessException("Don't be evil");
        }
        //todo: security check

        form.setPlan(topic.getActivePlan());
        form.setAccount(viewerAccount);

        String id = postService.createPost(form).replace("#", "");

        return "redirect:/topics/" + topicId + "/posts/" + id;
    }

    /**
     * Update a post.
     * The post can be of any type.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/_update", method=POST)
    public HttpEntity<?> updatePost(@PathVariable("postId") String postId, @Valid PostForm form, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {

        /**
        if(result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        */

        //checkAuthorizations("#"+postId);
        //todo: authorization check

        form.setId("#"+postId);
        postService.updatePost(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/topics/{topicId}/posts/{postId}/_delete", method=POST)
    public String deletePost(@PathVariable("postId") String postId, @Valid Command command, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {

        //checkAuthorizations("#"+postId);
        //todo: authorization check

        command.setId("#"+postId);
        command.setAccount(viewerAccount);
        String topicId = postService.deletePost(command);

        return "redirect:/topics/" + topicId + "/posts";
    }
}
