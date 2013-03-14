package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.User;
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
    public String updateTopic(@PathVariable("topicId") String topicId, @Valid TopicForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        Topic topic = topicService.getTopic("#" + topicId);
        topicService.checkOwner(topic, viewerAccount);
 
        form.setId("#"+topicId);
        topicService.updateTopic(form);
        return "redirect:/topics/" + topicId + "/objective";
    }

    @RequestMapping(value="/topics/{topicId}/_delete", method=POST)
    public String deleteTopic(@PathVariable("topicId") String topicId, @ModelAttribute("viewerAccount") Account viewerAccount) {
        
        Topic topic = topicService.getTopic("#" + topicId);
        topicService.checkOwner(topic, viewerAccount);
 
        topicService.deleteTopic("#"+topicId);
        return "redirect:/topics";
    }

    /**
     * Show topic.
     */
    @RequestMapping(value="/topics/{topicId}/objective")
    public String getTopic(@PathVariable("topicId") String topicId, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("current", "objective");

        Topic topic = topicService.getTopic("#" + topicId);
        model.put("topic", topic);

        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);

        return "objective";
    }

    /**
     * Get paged posts by topicId.
     */
    @RequestMapping(value="/topics/{topicId}/posts")
    public String getTopic(@PathVariable("topicId") String topicId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "all");

        Topic topic = topicService.getTopic("#" + topicId);
        model.put("topic", topic);

        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);

        page = ((page == null) || (page < 1)) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCount("#"+topicId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPosts("#"+topicId, page, size);
        model.put("posts", posts);

        return "posts";
    }

    /**
     * Get concepts
     */
    @RequestMapping(value="/topics/{topicId}/concepts")
    public String getConcepts(@PathVariable("topicId") String topicId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "concept");

        Topic topic = topicService.getTopic("#" + topicId);
        model.put("topic", topic);

        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCountByType("#"+topicId, "concept");
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPostsByType("#"+topicId, "concept", page, size);
        model.put("posts", posts);

        return "posts";
    }

    /**
     * Get concepts
     */
    @RequestMapping(value="/topics/{topicId}/notes")
    public String getNotes(@PathVariable("topicId") String topicId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "note");

        Topic topic = topicService.getTopic("#" + topicId);
        model.put("topic", topic);

        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);


        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCountByType("#"+topicId, "note");
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPostsByType("#"+topicId, "note", page, size);
        model.put("posts", posts);

        return "posts";
    }

    /**
     * Get concepts
     */
    @RequestMapping(value="/topics/{topicId}/tutorials")
    public String getTutorials(@PathVariable("topicId") String topicId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "tutorial");

        Topic topic = topicService.getTopic("#" + topicId);
        model.put("topic", topic);

        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);


        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCountByType("#"+topicId, "tutorial");
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPostsByType("#"+topicId, "tutorial", page, size);
        model.put("posts", posts);

        return "posts";
    }

    /**
     * Get paged questions by topicId.
     */
    @RequestMapping(value="/topics/{topicId}/questions")
    public String getQuestions(@PathVariable("topicId") String topicId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "question");

        Topic topic = topicService.getTopic("#" + topicId);
        model.put("topic", topic);

        Boolean isMember = false;
        Plan plan = topic.getActivePlan();
        if((plan != null) && (viewerAccount != null)) {
            isMember = planService.isMember(plan.getId(), viewerAccount.getId());
        }
        model.put("isMember", isMember);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getQuestionsCount("#"+topicId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Question> questions = topicService.getPagedQuestions("#"+topicId, page, size);
        model.put("questions", questions);

        return "questions";
    }

}
