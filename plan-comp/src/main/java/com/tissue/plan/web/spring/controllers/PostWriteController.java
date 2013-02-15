package com.tissue.plan.web.spring.controllers;

import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.core.plan.Plan;
import com.tissue.core.plan.Post;
import com.tissue.core.plan.Concept;
import com.tissue.core.plan.Note;
import com.tissue.core.plan.Tutorial;
import com.tissue.core.plan.Question;
import com.tissue.commons.IllegalAccessException;
import com.tissue.commons.social.service.UserService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.service.PlanService;
import com.tissue.plan.service.PostService;
/**
import com.tissue.plan.service.NoteService;
import com.tissue.plan.service.TutorialService;
import com.tissue.plan.service.QuestionService;
*/

import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Date;
import java.util.Locale;

@Controller
public class PostWriteController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected PlanService planService;

    @Autowired
    protected PostService postService;

    /**
    @Autowired
    protected NoteService noteService;

    @Autowired
    protected TutorialService tutorialService;

    @Autowired
    protected QuestionService questionService;
    */

    @RequestMapping(value="/plans/{planId}/posts/_form")
    public String newPost(@PathVariable("planId") String planId, Map model, Locale locale) {
        model.put("locale", locale);

        User user = userService.getViewer(SecurityUtil.getViewerId());
        model.put("viewer", user);

        Topic topic = planService.getTopic(planId);
        model.put("topic", topic);

        return "postForm";
    }
 
    /**
     * Add a post to the active plan.
     * The post can be any type.
     */
    @RequestMapping(value="/plans/{planId}/posts/_new", method=POST)
    public String addPost(@PathVariable("planId") String planId, @Valid PostForm form, BindingResult result, Map model) {

        if(result.hasErrors()) {
            throw new IllegalAccessException("Don't be evil");
        }

        Plan plan = new Plan();
        plan.setId(planId);
        form.setPlan(plan);

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        //user.setDisplayName(SecurityUtil.getDisplayName());
        form.setUser(user);

        /**
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setType(form.getType());

        post.setCreateTime(new Date());

        Plan plan = new Plan();
        plan.setId(planId);
        post.setPlan(plan);

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        user.setDisplayName(SecurityUtil.getDisplayName());
        post.setUser(user);

        String id = null;
        if("concept".equals(form.getType())) {
            id = addConcept(post);
        }
        if("note".equals(form.getType())) {
            id = addNote(post);
        }
        if("tutorial".equals(form.getType())) {
            id = addTutorial(post);
        }
        if("question".equals(form.getType())) {
            id = addQuestion(post);
        }
        */

        String id = postService.createPost(form);
        return "redirect:/posts/" + id;
    }

    /**
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
    */

}
