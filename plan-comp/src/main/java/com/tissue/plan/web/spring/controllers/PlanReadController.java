package com.tissue.plan.web.spring.controllers;

import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
import com.tissue.commons.security.core.userdetails.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Locale;

@Controller
public class PlanReadController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostService postService;

    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }

    @ModelAttribute("topic")
    public Topic prefetchTopic(@PathVariable("planId") String planId) {
        return topicService.getTopicByPlanId(planId);
    }

    @ModelAttribute("viewer")
    public UserDetailsImpl prefetchViewer() {
        return SecurityUtil.getUser();
    }

    /**
     * Get paged posts by planId.
     */
    @RequestMapping(value="/plans/{planId}") 
    public String getPosts(@PathVariable("planId") String planId,  @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        System.out.println("current plan: " + planId);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByPlanId(planId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = postService.getPagedPostsByPlanId(planId, page, size);
        model.put("posts", posts);

        return "topicPosts";
    }


}
