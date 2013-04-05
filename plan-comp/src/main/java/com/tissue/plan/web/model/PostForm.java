package com.tissue.plan.web.model;

import com.tissue.core.Account;
import com.tissue.plan.Plan;
import com.tissue.plan.command.PostCommand;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class PostForm extends ContentForm implements PostCommand {

    @NotNull
    @NotEmpty
    @Size(min=3, max=128)
    private String title;
    
    @NotNull
    private String type;

    private Plan plan;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Plan getPlan() {
        return plan;
    }
}
