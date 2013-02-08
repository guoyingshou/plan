package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.service.TopicService;

import org.springframework.validation.BindingResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;
//import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;

@Controller
public class TopicAjaxController {

    @Autowired
    private TopicService topicService;

    /**
     * Update topic.
     */
    @RequestMapping(value="/topics/{topicId}/update", method=POST)
    public HttpEntity<?> updateTopic(@PathVariable("topicId") String topicId, @Valid TopicForm form, BindingResult result, Map model) throws Exception {

        if(result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Topic topic = new Topic();
        topic.setId(topicId);
        topic.setTitle(form.getTitle());
        topic.setContent(form.getContent());

        User user = new User();
        user.setId(SecurityUtil.getViewerId());
        topic.setUser(user);

        Date date = new Date();
        topic.setUpdateTime(date);

        String tags = form.getTags();
        String[] splits = tags.split("\\s");
        topic.setTags(new HashSet(Arrays.asList(splits)));

        topicService.updateTopic(topic);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
