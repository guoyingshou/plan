package com.tissue.plan.web.spring.controllers;

//import com.tissue.commons.service.EventService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Controller
public class TestController {

    /**
    @Autowired
    private EventService eventService;
    */

    @RequestMapping(value="/t3")
    public String test3(@RequestParam("tid") String tid, Map model) {

        //eventService.test(tid);

        return "t3";
    }
  
}
