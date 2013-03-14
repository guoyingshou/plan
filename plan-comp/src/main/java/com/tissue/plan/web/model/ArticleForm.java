package com.tissue.plan.web.model;

import com.tissue.core.command.ArticleCommand;
import com.tissue.core.plan.Plan;

import java.io.Serializable;

public class ArticleForm extends PostForm implements ArticleCommand {
   
    private Plan plan;

    public void setPlan(Plan plan) {
        this.plan= plan;
    }

    public Plan getPlan() {
        return plan;
    }
}
