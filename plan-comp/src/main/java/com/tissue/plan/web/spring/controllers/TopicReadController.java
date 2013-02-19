package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.social.services.UserService;
import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.PlanForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PlanService;
import com.tissue.plan.services.PostService;

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
public class TopicReadController {

    @Autowired
    protected UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @Autowired
    private PostService postService;

    /**
    @ModelAttribute("locale")
    public String setupLocale(Locale locale) {
        return locale.toString();
    }
    */

    @ModelAttribute("viewer")
    public User prefetchViewer(Map model) {
        String viewerId = SecurityUtil.getViewerId();
        if(viewerId == null) {
            return null;    
        }
        return userService.getViewer(viewerId);
    }

    /**
     * Show topic.
     */
    @RequestMapping(value="/topics/{topicId}/objective")
    public String getTopic(@PathVariable("topicId") String topicId, Map model) {
        model.put("current", "objective");

        Topic topic = topicService.getTopic("#"+topicId);
        model.put("topic", topic);

        return "topic";
    }

    /**
     * Get paged posts by topicId.
     */
    @RequestMapping(value="/topics/{topicId}/posts")
    public String getTopic(@PathVariable("topicId") String topicId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        topicId = "#" + topicId;

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);

        page = ((page == null) || (page < 1)) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByTopicId(topicId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = postService.getPagedPostsByTopicId(topicId, page, size);
        model.put("posts", posts);

        return "topic";
    }

    /**
     * Get paged posts by topicId and type.
     */
    @RequestMapping(value="/topics/{topicId}/{type}/posts")
    public String getTopicsByType(@PathVariable("topicId") String topicId, @PathVariable(value="type") String type,  @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) throws Exception {

        topicId = "#" + topicId;
        model.put("current", type);

        Topic topic = topicService.getTopic(topicId);
        model.put("topic", topic);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByTopicIdAndType(topicId, type);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = postService.getPagedPostsByTopicIdAndType(topicId, type, page, size);
        model.put("posts", posts);

        return "topic";
    }

    /**
     * Get paged posts by planId.
     */
    @RequestMapping(value="/plans/{planId}") 
    public String getPosts(@PathVariable("planId") String planId,  @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        planId = "#" + planId;

        Topic topic = planService.getTopic(planId);
        model.put("topic", topic);

        System.out.println(">>>>current plan: " + planId);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = postService.getPostsCountByPlanId(planId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = postService.getPagedPostsByPlanId(planId, page, size);
        model.put("posts", posts);

        return "topic";
    }

    /**
     * Get specific post.
     */
    @RequestMapping(value="/posts/{postId}")
    public String getPost(@PathVariable("postId") String postId, Map model) {

        postId = "#" + postId;

        Topic topic = postService.getTopic(postId);
        model.put("topic", topic);

        Post post = postService.getPost(postId);
        model.put("post", post);

        if("question".equals(post.getType())) {
            return "questionDetail";
        }
        return "topic";
    }

}
