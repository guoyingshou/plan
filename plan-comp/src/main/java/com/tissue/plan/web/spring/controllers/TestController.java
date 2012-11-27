package com.tissue.plan.web.spring.controllers;

import com.tissue.commons.util.PagedDataHolder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.security.Principal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@Controller
public class TestController {

    @RequestMapping(value="/t3")
    public String test3(@RequestParam("page") String page, @RequestParam("pageSize") String pageSize, @RequestParam("count") String count, Map model) {
        int p = Integer.valueOf(page);
        int ps = Integer.valueOf(pageSize);
        int c = Integer.valueOf(count);

        PagedDataHolder pdh = new PagedDataHolder(p, ps, c);
        model.put("pagedData", pdh);

        return "t3";
    }

    @RequestMapping(value="/t1")
    public String test1(Principal principal) {
        return "t1";
    }

    @RequestMapping(value="/t2")
    public String test2(@Valid TestCommand command, BindingResult result) {
        System.out.println("+++++++++++");
        System.out.println(result);
        System.out.println("+++++++++++");
        System.out.println("name: " + command.getName());
        System.out.println("name: " + command.getCount());
        return "t1";
    }

    static class TestCommand {
        @NotNull
        private String name;

        @Min(value=20)
        private int count;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }

  
}
