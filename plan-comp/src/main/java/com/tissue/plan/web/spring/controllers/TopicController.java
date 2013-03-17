package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.Question;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.web.model.TopicForm;
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
public class TopicController {

    private static Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    /**
     * Add new topic.
     */
    @RequestMapping(value="/topics/_create", method=POST)
    public String addTopic(@Valid TopicForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {
        
        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        form.setAccount(viewerAccount);
        String topicId = topicService.addTopic(form).replace("#", "");
        return "redirect:/topics/" + topicId + "/posts";
    }

    /**
     * Update topic.
     */
    @RequestMapping(value="/topics/{topicId}/_update", method=POST)
    public String updateTopic(@PathVariable("topicId") Topic topic, @Valid TopicForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        topicService.checkOwner(topic, viewerAccount);
 
        form.setId(topic.getId());
        topicService.updateTopic(form);
        return "redirect:/topics/" + topic.getId().replace("#", "") + "/objective";
    }

    @RequestMapping(value="/topics/{topicId}/_delete", method=POST)
    public String deleteTopic(@PathVariable("topicId") Topic topic, @ModelAttribute("viewerAccount") Account viewerAccount) {
        
        topicService.checkOwner(topic, viewerAccount);
 
        topicService.deleteTopic(topic.getId());
        return "redirect:/topics";
    }

    /**
     * Show topic.
     */
    @RequestMapping(value="/topics/{topicId}/objective")
    public String getTopic(@PathVariable("topicId") Topic topic, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("topic", topic);
        model.put("current", "objective");

        topicService.checkMember(topic, viewerAccount, model);

        /**
        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);
        */

        return "objective";
    }

    /**
     * Get paged posts by topicId.
     */
    @RequestMapping(value="/topics/{topicId}/posts")
    public String getTopic(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("topic", topic);
        model.put("selected", "all");

        topicService.checkMember(topic, viewerAccount, model);
        /**
        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);
        */

        page = ((page == null) || (page < 1)) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCount(topic.getId());
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPosts(topic.getId(), page, size);
        model.put("posts", posts);

        return "posts";
    }

    /**
     * Get concepts
     */
    @RequestMapping(value="/topics/{topicId}/concepts")
    public String getConcepts(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("topic", topic);
        model.put("selected", "concept");

        topicService.checkMember(topic, viewerAccount, model);
        /**
        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);
        */

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCountByType(topic.getId(), "concept");
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPostsByType(topic.getId(), "concept", page, size);
        model.put("posts", posts);

        return "posts";
    }

    /**
     * Get concepts
     */
    @RequestMapping(value="/topics/{topicId}/notes")
    public String getNotes(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("topic", topic);
        model.put("selected", "note");

        topicService.checkMember(topic, viewerAccount, model);
        /**
        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);
        */

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCountByType(topic.getId(), "note");
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPostsByType(topic.getId(), "note", page, size);
        model.put("posts", posts);

        return "posts";
    }

    /**
     * Get concepts
     */
    @RequestMapping(value="/topics/{topicId}/tutorials")
    public String getTutorials(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("topic", topic);
        model.put("selected", "tutorial");

        topicService.checkMember(topic, viewerAccount, model);

        /**
        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);
        */

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCountByType(topic.getId(), "tutorial");
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPostsByType(topic.getId(), "tutorial", page, size);
        model.put("posts", posts);

        return "posts";
    }

    /**
     * Get paged questions by topicId.
     */
    @RequestMapping(value="/topics/{topicId}/questions")
    public String getQuestions(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("topic", topic);
        model.put("selected", "question");

        topicService.checkMember(topic, viewerAccount, model);
        /**
        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);
        */

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getQuestionsCount(topic.getId());
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Question> questions = topicService.getPagedQuestions(topic.getId(), page, size);
        model.put("questions", questions);

        return "questions";
    }

}
