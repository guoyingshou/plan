package com.tissue.plan.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.util.Pager;
import com.tissue.plan.Topic;
import com.tissue.plan.Plan;
import com.tissue.plan.Question;
import com.tissue.plan.web.model.PostForm;
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

    @Autowired
    private TopicService topicService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value="/topics/{topicId}/questions/_create")
    public String createQustionForm(@PathVariable("topicId") Topic topic, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "question");
        model.put("isMember", topicService.isMember(topic, viewerAccount));
        model.put("topic", topic);

        model.put("questionForm", new PostForm());
        return "createQuestionFormView";
    }

    @RequestMapping(value="/topics/{topicId}/questions/_create", method=POST)
    public String addQuestion(@PathVariable("topicId") Topic topic, @ModelAttribute("questionForm") @Valid PostForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            model.put("selected", "question");
            model.put("topic", topic);

            model.put("isMember", topicService.isMember(topic, viewerAccount));

            return "createQuestionFormView";
        }

        form.setPlan(topic.getActivePlan());
        form.setAccount(viewerAccount);

        String questionId = questionService.createPost(form);

        return "redirect:/questions/" + questionId.replace("#", "");
        
    }

    /**
     * Get specific question.
     */
    @RequestMapping(value="/questions/{questionId}")
    public String getPost(@PathVariable("questionId") Question question, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "question");

        Topic topic = question.getPlan().getTopic();

        model.put("question", question);
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));

        return "questionDetail";
    }

    @RequestMapping(value="/questions/{questionId}/_update")
    public String updateQuestionFormView(@PathVariable("questionId") Question question, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        model.put("selected", "question");

        Topic topic = question.getPlan().getTopic();
        model.put("topic", topic);
        model.put("isMember", topicService.isMember(topic, viewerAccount));
        model.put("questionForm", question);

        return "updateQuestionFormView";
 
    }

    @RequestMapping(value="/questions/{questionId}/_update", method=POST)
    public String updateQuestion(@PathVariable("questionId") Question question, @Valid @ModelAttribute("questionForm") PostForm form, BindingResult result, Map model, @ModelAttribute("viewerAccount") Account viewerAccount) {

        if(result.hasErrors()) {
            model.put("selected", "question");

            Topic topic = question.getPlan().getTopic();
            model.put("topic", topic);
            model.put("isMember", topicService.isMember(topic, viewerAccount));

            return "updateQuestionFormView";
        }

        form.setId(question.getId());
        questionService.updatePost(form);

        return "redirect:/questions/" + question.getId().replace("#","");
    }

    @RequestMapping(value="/questions/{questionId}/_delete", method=POST)
    public String deletePost(@PathVariable("questionId") Question question, @ModelAttribute("viewerAccount") Account viewerAccount) {
        questionService.deleteContent(question.getId());
        return "redirect:/questions/" + question.getId().replace("#","");
    }


}
