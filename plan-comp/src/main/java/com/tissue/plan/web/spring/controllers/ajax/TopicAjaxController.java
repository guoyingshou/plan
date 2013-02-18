package com.tissue.plan.web.spring.controllers.ajax;

import com.tissue.core.social.User;
import com.tissue.core.plan.Topic;
import com.tissue.commons.services.CommonService;
import com.tissue.commons.security.util.SecurityUtil;
import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.service.TopicService;
import com.tissue.plan.web.model.TopicForm;

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

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
//import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;
import javax.validation.Valid;

@Controller
public class TopicAjaxController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private TopicService topicService;

    /**
     * Update topic.
     */
    @RequestMapping(value="/topics/{topicId}/_update", method=POST)
    public HttpEntity<?> updateTopic(@PathVariable("topicId") String topicId, @Valid TopicForm form, BindingResult result, Map model) throws Exception {

        String viewerId = SecurityUtil.getViewerId();

        if(result.hasErrors() || !commonService.isResourceExist(topicId) || !commonService.isOwner(viewerId, topicId)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        form.setId("#"+topicId);
        topicService.updateTopic(form);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
