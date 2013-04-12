package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.core.User;
import com.tissue.commons.util.Pager;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.plan.Topic;
import com.tissue.plan.Post;
import com.tissue.plan.Question;
import com.tissue.plan.Answer;
import com.tissue.plan.web.model.ContentForm;
import com.tissue.plan.web.model.AnswerForm;
import com.tissue.plan.services.AnswerService;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AnswerController {

    private static Logger logger = LoggerFactory.getLogger(AnswerController.class);

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @Autowired
    private AnswerService answerService;

    @ModelAttribute("viewerActivePlansCount")
    private int setupViewerActivePlansCount() {
        return planService.getViewerActivePlansCount(SecurityUtil.getViewerAccountId());
    }

    /**
     * Add an answer to a specific post.
     * The post's type can only be 'question'.
     */
    @RequestMapping(value="/questions/{questionId}/answers/_create", method=POST)
    public String addAnswer(@PathVariable("questionId") Question question, @Valid AnswerForm form, BindingResult result, Map model) {

        if(result.hasErrors()) {
            model.put("selected", "question");

            Topic topic = question.getPlan().getTopic();

            model.put("question", question);
            model.put("topic", topic);
            model.put("isMember", topicService.isMember(topic, SecurityUtil.getViewerAccountId()));

            return "questionDetail";
        }

        Topic topic = question.getPlan().getTopic();

        form.setQuestion(question);
        Account viewerAccount = new Account();
        viewerAccount.setId(SecurityUtil.getViewerAccountId());
        form.setAccount(viewerAccount);
        answerService.addAnswer(form);

        model.clear();
        return "redirect:/questions/" + question.getId().replace("#","");
    }

    /**
     * Delete an answer.
     */
    @RequestMapping(value="/answers/{answerId}/_delete", method=POST)
    public String deleteAnswer(@PathVariable("answerId") Answer answer) {

        Topic topic = answer.getQuestion().getPlan().getTopic();
        answerService.deleteContent(answer.getId());

        return "redirect:/questions/" + answer.getQuestion().getId().replace("#","");
    }
}
