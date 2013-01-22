package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.ViewerSetter;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.PlanForm;
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
import java.util.Map;

@Controller
public class TopicReadController extends ViewerSetter {

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @Autowired
    private PostService postService;

    /**
     * Show topic.
     */
    @RequestMapping(value="/topics/{topicId}")
    public String getTopic(@PathVariable("topicId") String topicId, Map model) {

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);

        return "topicDetail";
    }

    /**
     * Get paged posts by topicId.
     */
    @RequestMapping(value="/topics/{topicId}/posts")
    public String getTopic(@PathVariable("topicId") String topicId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);

        page = ((page == null) || (page < 1)) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByTopicId(topicId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = postService.getPagedPostsByTopicId(topicId, page, size);
        model.put("posts", posts);

        return "posts";
    }

    /**
     * Get paged posts by topicId and type.
     */
    @RequestMapping(value="/topics/{topicId}/{type}/posts")
    public String getTopicsByType(@PathVariable("topicId") String topicId, @PathVariable(value="type") String type,  @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) throws Exception {

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByTopicIdAndType(topicId, type);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = postService.getPagedPostsByTopicIdAndType(topicId, type, page, size);
        model.put("posts", posts);

        return "posts";
    }

}
