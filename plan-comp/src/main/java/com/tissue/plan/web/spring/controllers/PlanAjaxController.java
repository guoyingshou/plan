package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;

import com.tissue.domain.profile.User;

import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.PlanForm;
import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Post;
import com.tissue.plan.service.TopicService;
import com.tissue.plan.service.PlanService;
import com.tissue.plan.service.PostService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Date;
import java.security.Principal;

@Controller
public class PlanAjaxController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostService postService;

    /**
     * Show form fragment for new plan creation.
     */
    @RequestMapping(value="/topics/{topicId}/plans")
    public String showPlanCreateForm(@PathVariable("topicId") String topicId, Map model) {
        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);
        model.put("viewer", SecurityUtil.getUser());
        return "fragments/planForm";
    }

    /**
     * Get paged posts by userId.
     */
    @RequestMapping(value="/users/{userId}/posts") 
    public String getPostsByUserId(@PathVariable("userId") String userId,  @RequestParam(value="page", required=false) Integer currentPage, @RequestParam(value="pageSize", required=false) Integer pageSize,  Map model) {

        List<Post> posts = postService.getPostsByUserId(userId);
        model.put("posts", posts);

        return "fragments/postList";
    }

}
