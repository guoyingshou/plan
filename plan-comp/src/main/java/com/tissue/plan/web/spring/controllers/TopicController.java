package com.tissue.plan.web.spring.controllers;

import com.tissue.core.command.Command;
import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.Question;
import com.tissue.core.plan.Answer;
import com.tissue.core.plan.AnswerComment;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.plan.PostMessageComment;
import com.tissue.core.plan.QuestionComment;
import com.tissue.core.security.UserDetailsImpl;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.util.Pager;
import com.tissue.commons.social.services.UserService;
import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.web.model.PlanForm;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.PostMessageForm;
import com.tissue.plan.web.model.PostMessageCommentForm;
import com.tissue.plan.web.model.QuestionCommentForm;
import com.tissue.plan.web.model.AnswerForm;
import com.tissue.plan.web.model.AnswerCommentForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PlanService;
import com.tissue.plan.services.PostService;
import com.tissue.plan.services.PostMessageService;
import com.tissue.plan.services.PostMessageCommentService;
import com.tissue.plan.services.AnswerService;
import com.tissue.plan.services.AnswerCommentService;
import com.tissue.plan.services.QuestionCommentService;

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
    protected UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostMessageService postMessageService;

    @Autowired
    private PostMessageCommentService postMessageCommentService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerCommentService answerCommentService;

    @Autowired
    private QuestionCommentService questionCommentService;


    @ModelAttribute("topic")
    public Topic getTopicByTopicId(@PathVariable("topicId") String topicId) {
        logger.debug("setting up topic from topicId: " + topicId);
        return topicService.getTopic(topicId);
    }

    /**
     * Update topic.
     */
    @RequestMapping(value="/topics/{topicId}/_update", method=POST)
    public HttpEntity<?> updateTopic(@PathVariable("topicId") String topicId, @Valid TopicForm form, BindingResult result, Map model) throws Exception {

        if(result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //checkExistence("#"+topicId);
        //checkAuthorizations("#"+topicId);
 
        form.setId("#"+topicId);
        topicService.updateTopic(form);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/topics/{topicId}/_delete", method=POST)
    public String deleteTopic(@PathVariable("topicId") String topicId, @Valid Command command, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {
        
        //checkAuthorizations("#"+topicId);

        command.setId("#"+topicId);

        command.setAccount(viewerAccount);

        topicService.deleteTopic(command);

        return "redirect:/topics";
    }

    /**
     * Show topic.
     */
    @RequestMapping(value="/topics/{topicId}/objective")
    public String getTopic(@PathVariable("topicId") String topicId, Map model) {
        model.put("current", "objective");
        return "topic";
    }

    /**
     * Get paged posts by topicId.
     */
    @RequestMapping(value="/topics/{topicId}/posts")
    public String getTopic(@PathVariable("topicId") String topicId, @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, Map model) {

        topicId = "#" + topicId;

        page = ((page == null) || (page < 1)) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCount(topicId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPosts(topicId, page, size);
        model.put("posts", posts);

        return "topic";
    }

    /**
     * Get paged posts by topicId and type.
     */
    @RequestMapping(value="/topics/{topicId}/{type}/posts")
    public String getTopicsByType(@PathVariable("topicId") String topicId, @PathVariable(value="type") String type,  @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) throws Exception {

        model.put("current", type);
        topicId = "#" + topicId;

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = topicService.getPostsCountByType(topicId, type);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = topicService.getPagedPostsByType(topicId, type, page, size);
        model.put("posts", posts);

        return "topic";
    }

    /**
     * ================================================
     * plan
     * ================================================
     */
    /**
     * Add a plan to the specific topic.
     */
    @RequestMapping(value="/topics/{topicId}/plans/_create", method=POST)
    public String addPlan(@PathVariable("topicId") String topicId, PlanForm form, Map model, @ModelAttribute("topic") Topic topic, @ModelAttribute("viewerAccount") Account viewerAccount) {

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

        //todo: authorization check
        
        planService.addMember("#"+planId, viewerAccount.getId());
        return "redirect:/topics/" + topicId + "/posts";
    }


    /**
     * Get paged posts by planId.
     */
    @RequestMapping(value="/topics/{topicId}/plans/{planId}") 
    public String getPosts(@PathVariable("planId") String planId,  @RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size,  Map model) {

        planId = "#" + planId;

        System.out.println(">>>>current plan: " + planId);

        page = (page == null) ? 1 : page;
        size = (size == null) ? 50 : size;
        long total = planService.getPostsCount(planId);
        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        List<Post> posts = planService.getPagedPosts(planId, page, size);
        model.put("posts", posts);

        return "topic";
    }

    /**
     * ================================================
     * post
     * ================================================
     */
    @RequestMapping(value="/topics/{topicId}/posts/_form")
    public String newPost(@PathVariable("topicId") String topicId, Map model) {

        //todo: authorization check
        return "postForm";
    }
 
    /**
     * Get specific post.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}")
    public String getPost(@PathVariable("postId") String postId, Map model) {

        postId = "#" + postId;

        Post post = postService.getPost(postId);
        model.put("post", post);

        String viewerAccountId = SecurityUtil.getViewerAccountId();
        System.out.println("=============");
        System.out.println("viewerAccountId: " + viewerAccountId);
        System.out.println("is plan active? " + post.getPlan().isActive());
        System.out.println("is member? " + post.getPlan().isMember(viewerAccountId));
        System.out.println("is owner? " + post.getPlan().isOwner(viewerAccountId));
        System.out.println("=============");

        if("question".equals(post.getType())) {
            return "questionDetail";
        }
        return "topic";
    }

    /**
     * Add a post to the active plan.
     * The post can be any type.
     */
    @RequestMapping(value="/topics/{topicId}/posts/_create", method=POST)
    public String addPost(@PathVariable("topicId") String topicId, @Valid PostForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount, @ModelAttribute("topic") Topic topic) {

        if(result.hasErrors()) {
            //throw new IllegalAccessException("Don't be evil");
        }
        //todo: security check

        form.setPlan(topic.getActivePlan());
        form.setAccount(viewerAccount);

        String id = postService.createPost(form).replace("#", "");

        return "redirect:/topics/" + topicId + "/posts/" + id;
    }

    /**
     * Update a post.
     * The post can be of any type.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/_update", method=POST)
    public HttpEntity<?> updatePost(@PathVariable("postId") String postId, @Valid PostForm form, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {

        /**
        if(result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        */

        //checkAuthorizations("#"+postId);
        //todo: authorization check

        form.setId("#"+postId);
        postService.updatePost(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/topics/{topicId}/posts/{postId}/_delete", method=POST)
    public String deletePost(@PathVariable("postId") String postId, @Valid Command command, BindingResult result, @ModelAttribute("viewerAccount") Account viewerAccount) {

        //checkAuthorizations("#"+postId);
        //todo: authorization check

        command.setId("#"+postId);
        command.setAccount(viewerAccount);
        String topicId = postService.deletePost(command);

        return "redirect:/topics/" + topicId + "/posts";
    }

    /**
     * ================================================
     * answer
     * ================================================
     */
    /**
     * Add an answer to a specific post.
     * The post's type can only be 'question'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/answers/_create", method=POST)
    public String addAnswer(@PathVariable("postId") String postId, @Valid AnswerForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Post post = new Post();
        post.setId("#"+postId);
        Question q = new Question(post);
        form.setQuestion(q);
        form.setAccount(viewerAccount);

        Answer answer = answerService.addAnswer(form);
        model.put("answer", answer);

        return "fragments/newAnswer";
    }

    /**
     * Update an answer.
     */
    @RequestMapping(value="/topics/{topicId}/answers/{answerId}/_update", method=POST)
    public HttpEntity<?> updateAnswer(@PathVariable("answerId") String answerId, @Valid AnswerForm form, BindingResult result, Map model) {

        //checkAuthorizations("#"+answerId);

        form.setId("#"+answerId);
        answerService.updateAnswer(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete an answer.
     */
    @RequestMapping(value="/topics/{topicId}/answers/{answerId}/_delete", method=POST)
    public HttpEntity<?> deleteAnswer(@PathVariable("answerId") String answerId) {

        //checkAuthorizations("#"+answerId);

        answerService.deleteAnswer("#"+answerId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Add a comment to the answer of a specific question.
     */
    @RequestMapping(value="/topics/{topicId}/answers/{answerId}/comments/_create", method=POST)
    public String addAnswerComment(@PathVariable("answerId") String answerId, @Valid AnswerCommentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        Answer answer = new Answer();
        answer.setId("#"+answerId);
        form.setAnswer(answer);
        form.setAccount(viewerAccount);

        AnswerComment comment = answerCommentService.addComment(form);
        model.put("comment", comment);

        return "fragments/newAnswerComment";
    }

    /**
     * Update an answer comment.
     */
    @RequestMapping(value="/topics/{topicId}/answerComments/{commentId}/_update", method=POST)
    public HttpEntity<?> updateAnswerComment(@PathVariable("commentId") String commentId, @Valid AnswerCommentForm form, BindingResult result, Map model) {

        //checkAuthorizations("#"+commentId);
        //todo: authorization check

        form.setId("#"+commentId);
        answerCommentService.updateComment(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete an answer comment.
     */
    @RequestMapping(value="/topics/{topicId}/answerComments/{commentId}/_delete", method=POST)
    public HttpEntity<?> deleteAnswerComment(@PathVariable("commentId") String commentId) {

        //checkAuthorizations("#"+commentId);

        answerCommentService.deleteComment("#"+commentId);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Add a message to a specific post.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/messages/_create", method=POST)
    public String addMessage(@PathVariable("postId") String postId, @Valid PostMessageForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        postId = "#" + postId;

        /**
        String viewerAccountId = SecurityUtil.getViewerAccountId();
        if(!commonService.isMemberOrOwner(viewerAccountId, postId)) {
            throw new InvalidParameterException("not member or owner");
        }
        */

        Post post = new Post();
        post.setId(postId);
        form.setPost(post);
        form.setAccount(viewerAccount);

        PostMessage postMessage = postMessageService.addMessage(form);
        model.put("postMessage", postMessage);
        return "fragments/newMessage";
    }
 
    /**
     * Update a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/messages/{msgId}/_update", method=POST)
    public HttpEntity<?> updateMessage(@PathVariable("msgId") String msgId, @Valid PostMessageForm form, BindingResult result) {

        //checkAuthorizations("#"+msgId);

        /**
        String viewerAccountId = SecurityUtil.getViewerAccountId();
        if(!commonService.isOwner(viewerAccountId, "#"+msgId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        */

        form.setId("#"+msgId);
        postMessageService.updatePostMessage(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a message.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/messages/{msgId}/_delete", method=POST)
    public HttpEntity<?> deleteMessage(@PathVariable("msgId") String msgId, Map model) {

        //checkAuthorizations("#"+msgId);

        postMessageService.deletePostMessage("#"+msgId);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Add a comment to the message of a specific post.
     * The post's type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/messages/{msgId}/comments/_create", method=POST)
    public String addMessageComment(@PathVariable("msgId") String msgId, @Valid PostMessageCommentForm form, BindingResult resutl, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {


        PostMessage msg = new PostMessage();
        msg.setId("#"+msgId);
        form.setPostMessage(msg);
        form.setAccount(viewerAccount);

        PostMessageComment comment = postMessageCommentService.addComment(form);
        model.put("messageComment", comment);

        return "fragments/newMessageComment";
    }

    /**
     * Update a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/messageComments/{commentId}/_update", method=POST)
    public HttpEntity<?> updateMessageComment(@PathVariable("commentId") String commentId, @Valid PostMessageCommentForm form, BindingResult result, Map model) {

        //checkAuthorizations("#"+commentId);

        form.setId("#"+commentId);
        postMessageCommentService.updateComment(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/topics/{topicId}/messageComments/{commentId}/_delete", method=POST)
    public HttpEntity<?> deleteMessageComment(@PathVariable("commentId") String commentId, Map model) {

        //checkAuthorizations("#"+commentId);

        postMessageCommentService.deleteComment("#"+commentId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Add a comment to a specific question(a kind of post).
     */
    @RequestMapping(value="/topics/{topicId}/posts/{postId}/questionComments/_create", method=POST)
    public String addQuestionComment(@PathVariable("postId") String postId, @Valid QuestionCommentForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {


        Post post = new Post();
        post.setId("#"+postId);
        form.setQuestion(post);
        form.setAccount(viewerAccount);

        QuestionComment comment = questionCommentService.addQuestionComment(form);
        model.put("questionComment", comment);

        return "fragments/newQuestionComment";
    }
 
    /**
     * Update a QuestionComment.
     */
    @RequestMapping(value="/topics/{topicId}/questionComments/{commentId}/_update", method=POST)
    public HttpEntity<?> updateQuestionComment(@PathVariable("commentId") String commentId, @Valid QuestionCommentForm form, BindingResult result, Map model) {

        //checkAuthorizations("#"+commentId);

        form.setId("#"+commentId);
        questionCommentService.updateQuestionComment(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a QuestionComment.
     */
    @RequestMapping(value="/topics/{topicId}/questionComments/{commentId}/_delete", method=POST)
    public HttpEntity<?> deleteQuestionComment(@PathVariable("commentId") String commentId, Map model) {

        //checkAuthorizations("#"+commentId);

        questionCommentService.deleteQuestionComment("#"+commentId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }



}
