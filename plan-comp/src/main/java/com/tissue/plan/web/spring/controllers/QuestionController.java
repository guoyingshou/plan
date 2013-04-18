package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.commons.util.SecurityUtil;
import com.tissue.commons.services.ViewerService;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Question;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.AnswerForm;
import com.tissue.plan.services.TopicService;
import com.tissue.plan.services.PlanService;
import com.tissue.plan.services.QuestionService;

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
public class QuestionController {

    private static Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private static String ROLE_NAME = "ROLE_ADMIN";

    @Autowired
    private ViewerService viewerService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PlanService planService;

    @Autowired
    private QuestionService questionService;

    @ModelAttribute("viewerActivePlansCount")
    private int setupViewerActivePlansCount() {
        return planService.getViewerActivePlansCount(SecurityUtil.getViewerAccountId());
    }

    @RequestMapping(value="/topics/{topicId}/questions/_create")
    public String createQustionForm(@PathVariable("topicId") Topic topic, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        viewerService.checkMembership(topic, viewerAccount);

        model.put("viewerAccount", viewerAccount);
        model.put("topic", topic);
        model.put("isMember", viewerService.isMember(topic, viewerAccount));
        
        model.put("selected", "question");
        model.put("questionForm", new PostForm());

        return "createQuestionFormView";
    }

    @RequestMapping(value="/topics/{topicId}/questions/_create", method=POST)
    public String addQuestion(@PathVariable("topicId") Topic topic, @ModelAttribute("questionForm") @Valid PostForm form, BindingResult result, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        viewerService.checkMembership(topic, viewerAccount);

        if(result.hasErrors()) {
            model.put("viewerAccount", viewerAccount);
            model.put("topic", topic);
            model.put("selected", "question");
            model.put("isMember", viewerService.isMember(topic, viewerAccount));

            return "createQuestionFormView";
        }

        form.setPlan(topic.getActivePlan());
        form.setAccount(viewerAccount);

        String questionId = questionService.createPost(form);

        model.clear();
        return "redirect:/questions/" + questionId.replace("#", "");
        
    }

    /**
     * Get specific question.
     */
    @RequestMapping(value="/questions/{questionId}")
    public String getPost(@PathVariable("questionId") Question question, Map model) {

        Topic topic = question.getPlan().getTopic();
        Account viewerAccount = viewerService.getViewerAccount();
        model.put("viewerAccount", viewerAccount);
        model.put("selected", "question");
        model.put("question", question);
        model.put("topic", topic);
        model.put("isMember", viewerService.isMember(topic, viewerAccount));
        model.put("answerForm", new AnswerForm());

        return "questionDetail";
    }

    @RequestMapping(value="/questions/{questionId}/_update")
    public String updateQuestionFormView(@PathVariable("questionId") Question question, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        question.checkPermission(viewerAccount, ROLE_NAME);
        //viewerService.checkOwnership(question, viewerAccount);

        Topic topic = question.getPlan().getTopic();

        model.put("viewerAccount", viewerAccount);
        model.put("topic", topic);
        model.put("isMember", viewerService.isMember(topic, viewerAccount));
        model.put("selected", "question");
        model.put("questionForm", question);

        return "updateQuestionFormView";
 
    }

    @RequestMapping(value="/questions/{questionId}/_update", method=POST)
    public String updateQuestion(@PathVariable("questionId") Question question, @Valid @ModelAttribute("questionForm") PostForm form, BindingResult result, Map model) {


        Account viewerAccount = viewerService.getViewerAccount();
        question.checkPermission(viewerAccount, ROLE_NAME);
        //viewerService.checkOwnership(question, viewerAccount);

        if(result.hasErrors()) {
            Topic topic = question.getPlan().getTopic();
            model.put("topic", topic);
            model.put("viewerAccount", viewerAccount);
            model.put("isMember", viewerService.isMember(topic, viewerAccount));
            model.put("selected", "question");

            return "updateQuestionFormView";
        }

        form.setId(question.getId());
        questionService.updatePost(form);

        model.clear();
        return "redirect:/questions/" + question.getId().replace("#","");
    }

    @RequestMapping(value="/questions/{questionId}/_delete", method=POST)
    public String deletePost(@PathVariable("questionId") Question question, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();
        question.checkPermission(viewerAccount, ROLE_NAME);
        //viewerService.checkOwnership(question, viewerAccount);

        questionService.deleteContent(question.getId());

        model.clear();
        return "redirect:/questions/" + question.getId().replace("#","");
    }

}
