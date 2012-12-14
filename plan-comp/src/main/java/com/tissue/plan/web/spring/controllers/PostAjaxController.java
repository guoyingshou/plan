package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.domain.profile.User;

import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;
import com.tissue.plan.web.model.AnswerForm;
import com.tissue.domain.plan.Topic;
import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;
import com.tissue.domain.plan.QuestionComment;
import com.tissue.domain.plan.Answer;
import com.tissue.domain.plan.AnswerComment;
import com.tissue.plan.service.PostService;
import com.tissue.plan.service.PostMessageService;
import com.tissue.plan.service.PostMessageCommentService;
import com.tissue.plan.service.QuestionCommentService;
import com.tissue.plan.service.AnswerService;
import com.tissue.plan.service.AnswerCommentService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.Map;
import java.util.Date;
import java.security.Principal;

@Controller
public class PostAjaxController {

    @Autowired
    protected PostService postService;

    @Autowired
    protected PostMessageService postMessageService;

    @Autowired
    protected PostMessageCommentService postMessageCommentService;

    @Autowired
    protected QuestionCommentService questionCommentService;

    @Autowired
    protected AnswerService answerService;

    @Autowired
    protected AnswerCommentService answerCommentService;

    /**
     * Show form for adding a new post.
     */
    @RequestMapping(value="/plans/{planId}/posts")
    public String showPostCreateForm(@PathVariable("planId") String planId, Locale locale, Map model) {

        model.put("planId", planId);
        model.put("viewer", SecurityUtil.getUser());

        String lang = locale.toLanguageTag();
        if(lang != null) 
            model.put("lang", lang);

        return "fragments/postForm";
    }
 
    /**
     * show post update form.
     */
    @RequestMapping(value="/posts/{postId}/edit")
    public String getPost(@PathVariable("postId") String postId, Map model, Locale locale) {

        Post post = postService.getPost(postId);
        model.put("post", post);

        return "fragments/postUpdateForm";

        /**
        String viewerId = SecurityUtil.getUserId();
        if(viewerId != null) {
             model.put("viewer", SecurityUtil.getUser());
        }

        String lang = locale.toLanguageTag();
        if(lang != null) 
            model.put("lang", lang);

        Post post = postService.getPost(postId);
        model.put("post", post);

        if("question".equals(post.getType())) {
            return "questionDetail";
        }
        return "postDetail";
        */
    }

    /**
     * Add a message to a specific post.
     * For ajax request only.
    @RequestMapping(value="/posts/{postId}/messages", method=POST)
    public String addMessage(@PathVariable("postId") String postId, @RequestParam("content") String content, Map model) {

        PostMessage message = new PostMessage();
        message.setContent(content);
        message.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        message.setUser(user);

        Post post = new Post();
        post.setId(postId);
        message.setPost(post);

        postMessageService.addMessage(message);

        post = postService.getPost(postId);
        model.put("post", post);

        return "postDetail";
    }

    @RequestMapping(value="/posts/{postId}/messages/{msgId}/comments", method=POST)
    public String addMessageComment(@PathVariable("postId") String postId, @PathVariable("msgId") String msgId, @RequestParam("content") String content, Map model) {

        PostMessageComment comment = new PostMessageComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        comment.setUser(user);

        PostMessage msg = new PostMessage();
        msg.setId(msgId);
        comment.setPostMessage(msg);

        postMessageCommentService.addComment(comment);

        Post post = postService.getPost(postId);
        model.put("post", post);

        return "postDetail";
    }
     */

    /**
     * Add a comment to a specific question.
     * For ajax request only.
    @RequestMapping(value="/questions/{postId}/comments", method=POST)
    public String addQuestionComment(@PathVariable("postId") String postId, @RequestParam("content") String content, Map model) {

        QuestionComment comment = new QuestionComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        comment.setUser(user);

        Post post = new Post();
        post.setId(postId);
        comment.setQuestion(post);

        questionCommentService.addComment(comment);

        post = postService.getPost(postId);
        model.put("post", post);

        return "questionDetail";
    }
     */


    /**
     * Add an answer to a specific post.
     * For ajax request only.
    @RequestMapping(value="/posts/{postId}/answers", method=POST)
    public String addAnswer(@PathVariable("postId") String postId, @RequestParam("content") String content, Map model) {

        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        answer.setUser(user);

        Post post = new Post();
        post.setId(postId);
        answer.setQuestion(post);

        answerService.addAnswer(answer);

        post = postService.getPost(postId);
        model.put("post", post);

        return "questionDetail";
    }

    @RequestMapping(value="/posts/{postId}/answers/{answerId}/comments", method=POST)
    public String addAnswerComment(@PathVariable("postId") String postId, @PathVariable("answerId") String answerId, @RequestParam("content") String content, Map model) {

        AnswerComment comment = new AnswerComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        comment.setUser(user);

        Answer answer = new Answer();
        answer.setId(answerId);
        comment.setAnswer(answer);

        answerCommentService.addComment(comment);

        Post post = postService.getPost(postId);
        model.put("post", post);

        return "questionDetail";
    }
     */


    //----------------- get posts by topic ---

    /**
     * Get post list by topic id.
     * Used only by ajax request.
    @RequestMapping(value="/topics/{topicId}/posts")
    public String getPostsByTopicId(@PathVariable("topicId") String topicId, Map model) {

        List<Post> posts = postService.getPostsByTopicId(topicId);
        model.put("posts", posts);
        model.put("viewer", SecurityUtil.getUser());

        return "postList";
    }
     */

    /**
     * Get paged posts by topicId and type.
     * Intended to by used by an ajax request.
    @RequestMapping(value="/topics/{topicId}/{type}")
    public String getTopicsByType(@PathVariable("topicId") String topicId, @PathVariable(value="type") String type,  @RequestParam(value="page", required=false) Integer currentPage, @RequestParam(value="pageSize", required=false) Integer pageSize,  Map model) throws Exception {

        List<Post> posts = postService.getPostsByTopicIdAndType(topicId, type);
        model.put("posts", posts);

        return "postList";
    }
     */

    //----------------- get posts by plan ---

    /**
     * Get paged posts by planId.
     * Intended to by used by an ajax request.
    @RequestMapping(value="/plans/{planId}") 
    public String getPosts(@PathVariable("planId") String planId,  @RequestParam(value="page", required=false) Integer currentPage, @RequestParam(value="pageSize", required=false) Integer pageSize,  Map model) {

        System.out.println("current plan: " + planId);

        List<Post> posts = postService.getPostsByPlanId(planId);
        model.put("posts", posts);

        return "postList";
    }
     */



    //----------------- get posts by user ---

    /**
     * Get paged posts by userId.
     * Intended to by used by an ajax request.
    @RequestMapping(value="/users/{userId}/posts") 
    public String getPostsByUserId(@PathVariable("userId") String userId,  @RequestParam(value="page", required=false) Integer currentPage, @RequestParam(value="pageSize", required=false) Integer pageSize,  Map model) {

        List<Post> posts = postService.getPostsByUserId(userId);
        model.put("posts", posts);

        return "postList";
    }
     */

}
