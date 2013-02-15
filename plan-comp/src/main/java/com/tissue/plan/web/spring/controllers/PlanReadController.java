package com.tissue.plan.web.spring.controllers;

import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.ViewerSetter;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.service.PlanService;
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
public class PlanReadController extends ViewerSetter {

    @Autowired
    private PlanService planService;

    @Autowired
    private PostService postService;

    /**
     * Get paged posts by planId.
     */
    @RequestMapping(value="/plans/{planId}") 
    public String getPosts(@PathVariable("planId") String planId,  @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        Topic topic = planService.getTopic(planId);
        model.put("topic", topic);

        System.out.println(">>>>current plan: " + planId);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByPlanId(planId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = postService.getPagedPostsByPlanId(planId, page, size);
        model.put("posts", posts);

        return "posts";
    }


}
