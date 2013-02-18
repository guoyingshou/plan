package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.social.User;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.Question;
import com.tissue.core.plan.Answer;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.service.AnswerService;
import com.tissue.plan.web.model.AnswerForm;

import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import javax.validation.Valid;

@Controller
public class AnswerAjaxController {

    @Autowired
    protected AnswerService answerService;

    /**
     * Add an answer to a specific post.
     * The post's type can only be 'question'.
     */
    @RequestMapping(value="/posts/{postId}/answers", method=POST)
    public String addAnswer(@PathVariable("postId") String postId, @Valid AnswerForm form, BindingResult result, Map model) {

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        form.setUser(user);

        Post post = new Post();
        post.setId("#"+postId);
        Question q = new Question(post);
        form.setQuestion(q);

        Answer answer = answerService.addAnswer(form);
        model.put("answer", answer);
        return "fragments/newAnswer";
    }

    /**
     * Update an answer.
     */
    @RequestMapping(value="/answers/{answerId}", method=POST)
    public HttpEntity<?> updateAnswer(@PathVariable("answerId") String answerId, @Valid AnswerForm form, BindingResult result, Map model) {

        form.setId("#"+answerId);
        answerService.updateAnswer(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete an answer.
     */
    @RequestMapping(value="/answers/{answerId}/delete", method=POST)
    public HttpEntity<?> deleteAnswer(@PathVariable("answerId") String answerId) {
        answerService.deleteAnswer("#"+answerId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


}
