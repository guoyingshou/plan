package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.social.User;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.PostMessage;
import com.tissue.core.plan.PostMessageComment;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;
import com.tissue.plan.service.PostMessageCommentService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Date;

@Controller
public class PostMessageCommentAjaxController {

    @Autowired
    protected PostMessageCommentService postMessageCommentService;

    /**
     * Add a comment to the message of a specific post.
     * The post's type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messages/{msgId}/comments", method=POST)
    public String addMessageComment(@PathVariable("msgId") String msgId, @RequestParam("content") String content, Map model) {

        PostMessageComment comment = new PostMessageComment();
        comment.setContent(content);
        comment.setCreateTime(new Date());

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        comment.setUser(user);

        PostMessage msg = new PostMessage();
        msg.setId(msgId);
        comment.setPostMessage(msg);

        comment = postMessageCommentService.addComment(comment);
        model.put("postMessageComment", comment);
        return "fragments/newPostMessageComment";
    }

    /**
     * Update a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messageComments/{commentId}", method=POST)
    @ResponseBody
    public String updateMessageComment(@PathVariable("commentId") String commentId, @RequestParam("content") String content, Map model) {

        PostMessageComment comment = new PostMessageComment();
        comment.setId(commentId);
        comment.setContent(content);
        postMessageCommentService.updateComment(comment);

        return "ok";
    }

    /**
     * Delete a PostMessageComment.
     * The post type can be 'concept', 'note' or 'tutorial'.
     */
    @RequestMapping(value="/messageComments/{commentId}/delete", method=POST)
    @ResponseBody
    public String deleteMessageComment(@PathVariable("commentId") String commentId, Map model) {
        postMessageCommentService.deleteComment(commentId);
        return "ok";
    }

}
