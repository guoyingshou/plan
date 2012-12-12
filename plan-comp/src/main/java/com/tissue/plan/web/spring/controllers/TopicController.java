package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
//import com.tissue.commons.util.PagedDataHolder;

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
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Date;

@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostService postService;

    /**
     * Show topic create form.
     */
    @RequestMapping(value="/topics")
    public String showTopicForm(Locale locale, Map model) {

        String lang = locale.toLanguageTag();
        if(lang != null) 
            model.put("lang", lang);

        model.put("user", SecurityUtil.getUser());
        return "topicForm";
    }

    /**
     * Add new topic.
     */
    @RequestMapping(value="/topics", method=POST)
    public String addTopic(TopicForm form, Map model) throws Exception {

        Topic topic = new Topic();
        topic.setTitle(form.getTitle());
        topic.setContent(form.getContent());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        topic.setUser(user);

        Date date = new Date();
        topic.setCreateTime(date);
        topic.setUpdateTime(date);

        String tags = form.getTags();
        String[] splits = tags.split("\\s");
        topic.setTags(new HashSet(Arrays.asList(splits)));

        topic = topicService.addTopic(topic);
        
        return "redirect:/plan/topics/" + topic.getId();
    }

    /**
     * Get paged posts by topicId.
     */
    @RequestMapping(value="/topics/{id}")
    public String getTopic(@PathVariable("id") String topicId, @RequestParam(value="page", required=false) Integer currentPage, @RequestParam(value="pageSize", required=false) Integer pageSize, Locale locale, Map model) {

        /**
        Integer page = currentPage == null ? 1 : currentPage;
        Integer size = pageSize == null ? PAGE_SIZE : pageSize;

        long count = postService.getPostsCountByTopicId(topicId);
        List<Post> posts = postService.getPagedPostsByTopicId(topicId, page, size);

        PagedDataHolder<Post> pdh = new PagedDataHolder(page, size, count);
        pdh.setPagedItems(posts);
        model.put("pagedData", pdh);
        */

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);

        List<Post> posts = postService.getPostsByTopicId(topicId);
        model.put("posts", posts);

        model.put("viewer", SecurityUtil.getUser());

        /**
        long count = topic.getPostsCount();

        int size = pageSize == null ? PAGE_SIZE : pageSize;
        int page = currentPage == null ? 1 : currentPage;

        int start = (page - 1) * size;
        int end = start + size;
        List<Post> posts = topic.getPosts().subList(start, end);

        PagedDataHolder<Post> pdh = new PagedDataHolder(page, size, count);
        pdh.setPagedItems(posts);
        model.put("pagedData", pdh);
        */
 
        return "topic";
    }

    /**
     * Get paged posts by topicId and type.
     */
    @RequestMapping(value="/topics/{topicId}/{type}")
    public String getTopicsByType(@PathVariable("topicId") String topicId, @PathVariable(value="type") String type,  @RequestParam(value="page", required=false) Integer currentPage, @RequestParam(value="pageSize", required=false) Integer pageSize,  Map model) throws Exception {

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);

        List<Post> posts = postService.getPostsByTopicIdAndType(topicId, type);
        model.put("posts", posts);

        model.put("viewer", SecurityUtil.getUser());

        return "topic";
    }


}
