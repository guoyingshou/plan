package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.util.Pager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(value="/t3")
    public String test3(@RequestParam("total") long total, @RequestParam("page") int page, @RequestParam("size") int size, Map model) {

        Pager pager = new Pager(total, page, size);
        model.put("pager", pager);

        return "t3";
    }
  
}
