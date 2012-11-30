package com.tissue.plan.web.spring.controllers;

/**
import com.tissue.plan.web.model.TopicForm;
import com.tissue.plan.web.model.PostForm;
import com.tissue.plan.web.model.MessageForm;
import com.tissue.plan.web.model.CommentForm;
import com.tissue.plan.web.model.AnswerForm;
import com.tissue.plan.domain.Topic;
import com.tissue.plan.domain.Plan;
import com.tissue.plan.domain.Post;
import com.tissue.plan.domain.Message;
import com.tissue.plan.domain.Comment;
import com.tissue.plan.domain.Answer;
import com.tissue.plan.service.TopicService;
import com.tissue.plan.service.PlanService;
import com.tissue.plan.service.PostService;
*/

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.multipart.MultipartFile;

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
public class ImageController {

    @RequestMapping(value="/images", method=POST)
    public String addImage(@RequestParam("CKEditorFuncNum") String num, MultipartFile file, Principal principal, Map model) {
        model.put("num", num);
        model.put("imageUrl", "http://localhost/images/1.jpg");
        //model.put("msg", "OK");
        return "ckeditorImageCallback"; 
    }

}