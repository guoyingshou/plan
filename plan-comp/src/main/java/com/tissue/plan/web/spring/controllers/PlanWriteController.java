package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.web.model.PlanForm;
import com.tissue.plan.services.PlanService;

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

@Controller
public class PlanWriteController {

    @Autowired
    private PlanService planService;

    /**
     * Add a plan to the specific topic.
     */
    @RequestMapping(value="/topics/{topicId}/plans", method=POST)
    public String addPlan(@PathVariable("topicId") String topicId, PlanForm form, Map model) {

        Topic topic = new Topic();
        topic.setId("#"+topicId);
        form.setTopic(topic);

        Account account = new Account();
        account.setId(SecurityUtil.getViewerId());
        form.setAccount(account);

        planService.addPlan(form);
        return "redirect:/topics/" + topicId + "/posts";
    }

    /**
     * Join a plan.
     */
    @RequestMapping(value="/topics/{topicId}/plans/{planId}/join")
    public String joinPlan(@PathVariable("topicId") String topicId, @PathVariable("planId") String planId, Map model) {
        planService.addMember("#"+planId, SecurityUtil.getViewerId());
        return "redirect:/topics/" + topicId + "/posts";
    }


}
