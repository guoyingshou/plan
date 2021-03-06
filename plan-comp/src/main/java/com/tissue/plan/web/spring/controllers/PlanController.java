package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.commons.services.ViewerService;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Post;
import com.tissue.plan.web.model.PlanForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PlanService;

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
public class PlanController {

    private static Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private ViewerService viewerService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @ModelAttribute("viewerActivePlansCount")
    private int setupViewerActivePlansCount() {
        return planService.getViewerActivePlansCount(SecurityUtil.getViewerAccountId());
    }

    @RequestMapping(value="/topics/{topicId}/plans/_create")
    public String addPlan(@PathVariable("topicId") Topic topic, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();

        model.put("viewerAccount", viewerAccount);
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));
        model.put("selected", "objective");
        model.put("planForm", new PlanForm());

        return "createPlanFormView";
    }


    /**
     * Add a plan to the specific topic.
     */
    @RequestMapping(value="/topics/{topicId}/plans/_create", method=POST)
    public String addPlan(@PathVariable("topicId") Topic topic, PlanForm form, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();

        form.setTopic(topic);
        form.setAccount(viewerAccount);

        planService.addPlan(form);
        model.clear();
        return "redirect:/topics/" + topic.getId().replace("#","") + "/objective";
    }

    /**
     * Join a plan.
     */
    @RequestMapping(value="/plans/{planId}/_join")
    public String joinPlan(@PathVariable("planId") Plan plan, Map model) {
        planService.addMember(plan.getId(), SecurityUtil.getViewerAccountId());

        model.clear();
        return "redirect:/topics/" + plan.getTopic().getId().replace("#","") + "/posts";
    }

    /**
     * Get paged posts by planId.
     */
    @RequestMapping(value="/plans/{planId}/posts") 
    public String getPosts(@PathVariable("planId") Plan plan, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        Topic topic = plan.getTopic();
        Account viewerAccount = viewerService.getViewerAccount();

        model.put("viewerAccount", viewerAccount);
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));
        model.put("selected", "all");

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = planService.getPostsCount(plan.getId());
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = planService.getPagedPosts(plan.getId(), page, size);
        model.put("posts", posts);

        return "posts";
    }

}
