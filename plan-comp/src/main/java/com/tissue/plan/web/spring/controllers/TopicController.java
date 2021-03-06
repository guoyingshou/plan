package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.commons.services.ViewerService;
import com.tissue.core.User;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Post;
import com.tissue.plan.Question;
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
import java.security.AccessControlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class TopicController {

    private static Logger logger = LoggerFactory.getLogger(TopicController.class);

    private static String ROLE_NAME = "ROLE_ADMIN";

    @Autowired
    private ViewerService viewerService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @ModelAttribute("viewerActivePlansCount")
    private int setupViewerActivePlansCount() {
        return 6;
        //return planService.getViewerActivePlansCount(SecurityUtil.getViewerAccountId());
    }

    @RequestMapping(value="/topics/_create")
    public String showTopicFormView(Map model) {
        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount.hasRole("ROLE_EVIL")) {
            throw new AccessControlException("Evil account: " + viewerAccount);
        }

        model.put("viewerAccount", viewerAccount);
        model.put("selected", "topics");
        model.put("topicForm", new TopicForm());

        return "createTopicFormView";
    }

    /**
     * Add new topic.
     */
    @RequestMapping(value="/topics/_create", method=POST)
    public String addTopic(@Valid TopicForm form, BindingResult result, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount.hasRole("ROLE_EVIL")) {
            throw new AccessControlException("Evil account: " + viewerAccount);
        }
       
        if(result.hasErrors()) {
            model.put("selected", "topics");
            model.put("viewerAccount", viewerAccount);
            return "createTopicFormView";
        }

        form.setAccount(viewerAccount);
        String topicId = topicService.addTopic(form);

        model.clear();
        return "redirect:/topics/" + topicId.replace("#","") + "/posts";
    }

    @RequestMapping(value="/topics/{topicId}/_update")
    public String updateTopicFormView(@PathVariable("topicId") Topic topic,Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount.hasRole("ROLE_EVIL")) {
            throw new AccessControlException("Evil account: " + viewerAccount);
        }
 
        topic.checkPermission(viewerAccount, ROLE_NAME);

        model.put("viewerAccount", viewerAccount);
        model.put("selected", "objective");
        model.put("topic", topic);
        model.put("topicForm", topic);

        return "updateTopicFormView";
    }
 
    /**
     * Update topic.
     */
    @RequestMapping(value="/topics/{topicId}/_update", method=POST)
    public String updateTopic(@PathVariable("topicId") Topic topic, @Valid TopicForm form, BindingResult result, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount.hasRole("ROLE_EVIL")) {
            throw new AccessControlException("Evil account: " + viewerAccount);
        }
        topic.checkPermission(viewerAccount, ROLE_NAME);

        if(result.hasErrors()) {
            model.put("viewerAccount", viewerAccount);
            model.put("selected", "objective");
            model.put("topic", topic);
            model.put("isMember", topicService.isMember(topic, viewerAccount));
            return "updateTopicFormView";
        }
 
        form.setId(topic.getId());
        topicService.updateTopic(form);

        model.clear();
        return "redirect:/topics/" + topic.getId().replace("#", "") + "/objective";
    }

    @RequestMapping(value="/topics/{topicId}/_delete", method=POST)
    public String deleteTopic(@PathVariable("topicId") Topic topic, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        if(viewerAccount.hasRole("ROLE_EVIL")) {
            throw new AccessControlException("Evil account: " + viewerAccount);
        }
        topic.checkPermission(viewerAccount, ROLE_NAME);
       
        topicService.deleteContent(topic.getId());
        model.clear();
        return "redirect:/topics";
    }

    /**
     * Show topic.
     */
    @RequestMapping(value="/topics/{topicId}/objective")
    public String getObjective(@PathVariable("topicId") Topic topic, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "objective");
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));

        return "topic";
    }

    /**
     * Get paged posts by topicId.
     */
    @RequestMapping(value="/topics/{topicId}/posts")
    public String getPosts(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "all");
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));

        page = ((page == null) || (page < 1)) ? 1 : page;
        size = (size == null) ? 12 : size;
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
    public String getConcepts(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "concept");
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));

        page = (page == null) ? 1 : page;
        size = (size == null) ? 12 : size;
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
    public String getNotes(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "note");
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));

        page = (page == null) ? 1 : page;
        size = (size == null) ? 12 : size;
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
    public String getTutorials(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "tutorial");
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));

        page = (page == null) ? 1 : page;
        size = (size == null) ? 12 : size;
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
    public String getQuestions(@PathVariable("topicId") Topic topic, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);

        model.put("selected", "question");
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));

        page = (page == null) ? 1 : page;
        size = (size == null) ? 12 : size;
        long total = topicService.getPostsCountByType(topic.getId(), "question");
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPostsByType(topic.getId(), "question", page, size);
        model.put("posts", posts);

        return "posts";
    }

}
