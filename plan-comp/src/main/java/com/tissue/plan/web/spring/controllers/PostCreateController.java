package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.Concept;
import com.tissue.core.plan.Note;
import com.tissue.core.plan.Tutorial;
import com.tissue.core.plan.Question;
import com.tissue.commons.exceptions.IllegalAccessException;
import com.tissue.commons.social.services.UserService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.services.PlanService;
import com.tissue.plan.services.PostService;

import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Date;
import java.util.Locale;
import java.util.List;

@Controller
public class PostCreateController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected PlanService planService;

    @Autowired
    protected PostService postService;

    @RequestMapping(value="/plans/{planId}/posts/_form")
    public String newPost(@PathVariable("planId") String planId, Map model, Locale locale) {
        model.put("locale", locale);

        User user = userService.getViewer(SecurityUtil.getViewerId());
        model.put("viewer", user);

        Topic topic = planService.getTopic(planId);
        model.put("topic", topic);

        return "postForm";
    }
 
    /**
     * Add a post to the active plan.
     * The post can be any type.
     */
    @RequestMapping(value="/plans/{planId}/posts/_create", method=POST)
    public String addPost(@PathVariable("planId") String planId, @Valid PostForm form, BindingResult result, Map model) {

        if(result.hasErrors()) {
            throw new IllegalAccessException("Don't be evil");
        }

        String viewerId = SecurityUtil.getViewerId();
        Plan plan = planService.getPlan("#"+planId);

        if((plan == null) || !(plan.isOwner(viewerId) || plan.isMember(viewerId))) {
            throw new IllegalAccessException("Don't be evil");
        }

        form.setPlan(plan);

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        form.setUser(user);

        String id = postService.createPost(form).replace("#", "");
        return "redirect:/posts/" + id;
    }

}
