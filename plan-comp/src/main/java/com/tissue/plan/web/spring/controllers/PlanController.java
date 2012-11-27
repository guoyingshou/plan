package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
//import com.tissue.core.util.PagedDataHolder;

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
public class PlanController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    /**
     * Show form for new plan creation.
     * Intended to by used by ajax.
     */
    @RequestMapping(value="/topics/{topicId}/plans")
    public String showPlanCreateForm(@PathVariable("topicId") String topicId, Map model) {
        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);
        model.put("viewer", SecurityUtil.getUser());
        return "planForm";
    }

    /**
     * Add a plan to the specific topic.
     */
    @RequestMapping(value="/topics/{topicId}/plans", method=POST)
    public String addPlan(@PathVariable("topicId") String topicId, PlanForm form, Map model) {

        Plan plan = new Plan();
        plan.setDuration(form.getDuration());
        plan.setCreateTime(new Date());

        Topic topic = new Topic();
        topic.setId(topicId);
        plan.setTopic(topic);

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        plan.setUser(user);

        plan = planService.addPlan(plan);

        System.out.println("current plan: " + plan.getId());

        return "redirect:/plan/topics/" + topicId;
        //return "postList";
    }

    /**
     * Join a plan.
     */
    @RequestMapping(value="/topics/{topicId}/plans/{planId}/join")
    public String joinPlan(@PathVariable("topicId") String topicId, @PathVariable("planId") String planId, Map model) {
        planService.addMember(planId, SecurityUtil.getUserId());
        return "redirect:/plan/topics/" + topicId;
    }


}
