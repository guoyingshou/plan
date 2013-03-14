package com.tissue.plan.web.model;

import com.tissue.core.command.QuestionCommand;
import com.tissue.core.plan.Plan;

import java.io.Serializable;

public class QuestionForm extends PostForm implements QuestionCommand {
   
    private Plan plan;

    public void setPlan(Plan plan) {
        this.plan= plan;
    }

    public Plan getPlan() {
        return plan;
    }
}
