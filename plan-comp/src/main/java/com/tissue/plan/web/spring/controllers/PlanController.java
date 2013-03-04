package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
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
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    /**
     * Add a plan to the specific topic.
     */
    @RequestMapping(value="/topics/{topicId}/plans/_create", method=POST)
    public String addPlan(@PathVariable("topicId") String topicId, PlanForm form, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Topic topic = topicService.getTopic(topicId);
        form.setTopic(topic);
        form.setAccount(viewerAccount);

        planService.addPlan(form);
        return "redirect:/topics/" + topicId + "/posts";
    }

    /**
     * Join a plan.
     */
    @RequestMapping(value="/topics/{topicId}/plans/{planId}/_join")
    public String joinPlan(@PathVariable("topicId") String topicId, @PathVariable("planId") String planId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        planId = "#" + planId;
        //check if plan exist. If not, an exception from the db will be thrown.
        Plan plan = planService.getPlan(planId);

        planService.addMember(planId, viewerAccount.getId());
        return "redirect:/topics/" + topicId + "/posts";
    }

    /**
     * Get paged posts by planId.
     */
    @RequestMapping(value="/topics/{topicId}/plans/{planId}/posts") 
    public String getPosts(@PathVariable("topicId") String topicId, @PathVariable("planId") String planId,  @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        topicId = "#" + topicId;
        planId = "#" + planId;

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = planService.getPostsCount(planId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = planService.getPagedPosts(planId, page, size);
        model.put("posts", posts);

        return "topic";
    }

}
