package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.Account;
import com.tissue.core.social.User;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.Question;
import com.tissue.core.plan.Answer;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.commons.controllers.AccessController;
import com.tissue.plan.services.AnswerService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Date;
import javax.validation.Valid;

@Controller
public class AnswerController extends AccessController {

    @Autowired
    protected AnswerService answerService;

    /**
     * Add an answer to a specific post.
     * The post's type can only be 'question'.
     */
    @RequestMapping(value="/posts/{postId}/answers/_create", method=POST)
    public String addAnswer(@PathVariable("postId") String postId, @Valid AnswerForm form, BindingResult result, Map model, @ModelAttribute("viewer") User viewer) {

        Account account = new Account();
        account.setId(SecurityUtil.getViewerAccountId());
        account.setUser(viewer);
        form.setAccount(account);

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
    @RequestMapping(value="/answers/{answerId}/_update", method=POST)
    public HttpEntity<?> updateAnswer(@PathVariable("answerId") String answerId, @Valid AnswerForm form, BindingResult result, Map model) {

        checkAuthorizations("#"+answerId);

        form.setId("#"+answerId);
        answerService.updateAnswer(form);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * Delete an answer.
     */
    @RequestMapping(value="/answers/{answerId}/_delete", method=POST)
    public HttpEntity<?> deleteAnswer(@PathVariable("answerId") String answerId) {

        checkAuthorizations("#"+answerId);

        answerService.deleteAnswer("#"+answerId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


}
