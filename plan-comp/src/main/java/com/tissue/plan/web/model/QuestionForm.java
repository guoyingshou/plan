package com.tissue.plan.web.model;

import com.tissue.core.command.Command;
import com.tissue.core.command.QuestionCommand;
import com.tissue.core.social.User;
import com.tissue.core.plan.Plan;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class QuestionForm extends Command implements QuestionCommand {

    @NotNull
    @NotEmpty
    private String title;
    
    private Plan plan;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPlan(Plan plan) {
        this.plan= plan;
    }

    public Plan getPlan() {
        return plan;
    }
}
