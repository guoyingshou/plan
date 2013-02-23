package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.plan.PostMessageComment;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.controllers.AccessController;
import com.tissue.plan.services.PostMessageCommentService;
import com.tissue.plan.web.model.PostMessageCommentForm;

import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Date;
import javax.validation.Valid;

@Controller
public class PostMessageCommentController extends AccessController {

    @Autowired
    protected PostMessageCommentService postMessageCommentService;

    /**
     * Add a comment to the message of a specific post.
     * The post's type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messages/{msgId}/comments/_create", method=POST)
    public String addMessageComment(@PathVariable("msgId") String msgId, @Valid PostMessageCommentForm form, BindingResult resutl, Map model, @ModelAttribute("viewer") User viewer) {

        Account account = new Account();
        account.setId(SecurityUtil.getViewerAccountId());
        account.setUser(viewer);
        form.setAccount(account);

        PostMessage msg = new PostMessage();
        msg.setId("#"+msgId);
        form.setPostMessage(msg);

        PostMessageComment comment = postMessageCommentService.addComment(form);
        model.put("messageComment", comment);
        return "fragments/newMessageComment";
    }

    /**
     * Update a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messageComments/{commentId}/_update", method=POST)
    public HttpEntity<?> updateMessageComment(@PathVariable("commentId") String commentId, @Valid PostMessageCommentForm form, BindingResult result, Map model) {

        checkAuthorizations("#"+commentId);

        form.setId("#"+commentId);
        postMessageCommentService.updateComment(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messageComments/{commentId}/_delete", method=POST)
    public HttpEntity<?> deleteMessageComment(@PathVariable("commentId") String commentId, Map model) {

        checkAuthorizations("#"+commentId);

        postMessageCommentService.deleteComment("#"+commentId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
