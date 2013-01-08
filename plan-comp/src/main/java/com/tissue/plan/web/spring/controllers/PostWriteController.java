package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.domain.profile.User;

import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;

import com.tissue.domain.plan.Plan;
import com.tissue.domain.plan.Post;
import com.tissue.domain.plan.Concept;
import com.tissue.domain.plan.Note;
import com.tissue.domain.plan.Tutorial;
import com.tissue.domain.plan.Question;
import com.tissue.domain.plan.PostMessage;
import com.tissue.domain.plan.PostMessageComment;
import com.tissue.plan.service.PostService;
import com.tissue.plan.service.ConceptService;
import com.tissue.plan.service.NoteService;
import com.tissue.plan.service.TutorialService;
import com.tissue.plan.service.QuestionService;
import com.tissue.plan.service.PostMessageService;
import com.tissue.plan.service.PostMessageCommentService;

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
public class PostWriteController {

    @Autowired
    protected PostService postService;

    @Autowired
    protected ConceptService conceptService;

    @Autowired
    protected NoteService noteService;

    @Autowired
    protected TutorialService tutorialService;

    @Autowired
    protected QuestionService questionService;

    @Autowired
    protected PostMessageService postMessageService;

    @Autowired
    protected PostMessageCommentService postMessageCommentService;


    /**
     * Add a post to the active plan.
     * The post can be any type.
     */
    @RequestMapping(value="/plans/{planId}/posts", method=POST)
    public String addPost(@PathVariable("planId") String planId, PostForm form, Map model) {

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setType(form.getType());

        post.setCreateTime(new Date());

        Plan plan = new Plan();
        plan.setId(planId);

        User user = new User();
        user.setId(SecurityUtil.getUserId());
        user.setDisplayName(SecurityUtil.getDisplayName());

        post.setPlan(plan);
        post.setUser(user);


        if("concept".equals(form.getType())) {
            String id = addConcept(post);
            return "redirect:/posts/" + id;
        }

        if("note".equals(form.getType())) {
            String id = addNote(post);
            return "redirect:/posts/" + id;
        }

        if("tutorial".equals(form.getType())) {
            String id = addTutorial(post);
            return "redirect:/posts/" + id;
        }

        if("question".equals(form.getType())) {
            String id = addQuestion(post);
            return "redirect:/posts/" + id;
        }

        return "error";

    }

    private String addConcept(Post post) {
        Concept concept = new Concept(post);
        concept = conceptService.addConcept(concept);
        return concept.getId();
    }

    private String addNote(Post post) {
        Note note = new Note(post);
        note = noteService.addNote(note);
        return note.getId();
    }

    private String addTutorial(Post post) {
        Tutorial tutorial = new Tutorial(post);
        tutorial = tutorialService.addTutorial(tutorial);
        return tutorial.getId();
    }

    private String addQuestion(Post post) {
        Question question = new Question(post);
        question = questionService.addQuestion(question);
        return question.getId();
    }

}
